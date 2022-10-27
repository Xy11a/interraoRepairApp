package ru.interrao.itrepair.Web.Controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.CompositeEntity.UsedComponents;
import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;
import ru.interrao.itrepair.Web.Entity.PK.UsedComponentsPK;
import ru.interrao.itrepair.Web.Entity.Reports.Report;
import ru.interrao.itrepair.Web.Entity.Reports.ReportStatusEnum;
import ru.interrao.itrepair.Web.Services.ComponentService;
import ru.interrao.itrepair.Web.Services.ReportService;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UsedComponentServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService Userservice;

    @Autowired
    private ComponentService componentService;

    @Autowired
    UsedComponentServiceImpl usedComponentService;

    @GetMapping("/reports")
    public String defaultPage(Authentication authentication, Model model) {
        User user = Userservice.getUserByUsername(authentication.getName());
        List<Report> list = reportService.getAll();
        model.addAttribute("listOfTypes", Arrays.asList(ReportStatusEnum.values()));
        model.addAttribute("CurrentUser", user);
        model.addAttribute("CurrentReports", list);
        return "reports/report";
    }

    @PostMapping("/reports")
    public String deleteComponent(@RequestParam(required = true, defaultValue = "") Integer reportId, @RequestParam(required = true, defaultValue = "") ReportStatusEnum action, Model model) {
        Report report = reportService.get(reportId).get();
        System.out.println(report);
        report.setStatus(action);
        reportService.save(report);
        return "redirect:/reports";
    }

    @GetMapping("/reports/new")
    public String createComponent(Authentication authentication, Model model) {
        User user = Userservice.getUserByUsername(authentication.getName());
        Report report = new Report();
        model.addAttribute("reportObj", report);
        model.addAttribute("CurrentUser", user);
        model.addAttribute("listOfStatus", Arrays.asList(ReportStatusEnum.values()));
        return "reports/newReport";
    }

    @PostMapping("/reports/new")
    public String submitForm(@ModelAttribute("reportObj") Report report, Authentication authentication, Model model) {
        User user = Userservice.getUserByUsername(authentication.getName());
        report.setOwner(user.getUsername());
        report.setDate(new Date());
        report.setStatus(ReportStatusEnum.CREATED);
        reportService.save(report);
        return "redirect:/reports";
    }

    @GetMapping("/reports/used/{id}")
    public String getUsedComps(@PathVariable(name = "id") Integer reportId, Model model, Authentication authentication) {
        ///--User--///
        User user = Userservice.getUserByUsername(authentication.getName());

        ///--Components--///
        List<UsedComponents> usedComs = usedComponentService.getAll();

        usedComs = usedComs.stream().filter(components -> components.getKey().getReportId() == reportId).collect(Collectors.toList());

        System.out.println(usedComs);

        List<Integer> usedComsId = new ArrayList<>();

        usedComs.forEach(c -> usedComsId.add(c.getKey().getComponentId()));

        HashMap<UsedComponents, String> hashMap = getCompNameAmountMap(usedComs);


        UsedComponents newComp = new UsedComponents();
        newComp.setKey(new UsedComponentsPK());
        newComp.getKey().setReportId(reportId);
        newComp.setAmount(0);

        List<Component> compOptions = componentService.getAll();
        compOptions.removeIf(c -> usedComsId.contains(c.getId()));

        ///--Attr--///
        model.addAttribute("CurrentUser", user);
        model.addAttribute("CurrentsComps", hashMap);
        model.addAttribute("options", compOptions);
        model.addAttribute("newComp", newComp);


        return "usedComponent/index";
    }

    private HashMap<UsedComponents, String> getCompNameAmountMap(List<UsedComponents> usedComs) {
        HashMap<UsedComponents, String> hashMap = new HashMap<>();
        for (UsedComponents comp : usedComs)
            hashMap.put(comp, componentService.get(comp.getKey().getComponentId()).get().getName());
        return hashMap;
    }


    @PostMapping("/reports/used/{id}")
    public String updateUsedComp(@PathVariable(name = "id") int reportId, @RequestParam(name = "amount") int compAmount, @RequestParam(name = "compId") int compId, @RequestParam(name = "action") String action) {
        switch (action) {
            case "del":
                usedComponentService.deleteById(reportId, compId);
                break;
            case "upd":
                UsedComponents component = usedComponentService.get(reportId, compId).get();
                component.setAmount(compAmount);
                usedComponentService.save(component);
                break;

        }
        return "redirect:/reports/used/" + reportId;
    }

    @PostMapping("/reports/used/{id}/create")
    public String saveComp(@PathVariable(name = "id") int reportId, @ModelAttribute("newComp") @Valid UsedComponents component) {
        System.out.println("Sad Cat");
        System.out.println(component.getKey());
        component.getKey().setReportId(reportId);
        usedComponentService.save(component);
        return "redirect:/reports/used/" + reportId;
    }

    @GetMapping("/reports/used/{id}/download")
    private void createPDF(@PathVariable(name = "id") int reportId, HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=component" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<UsedComponents> used = usedComponentService.getAll();
        used = used.stream().filter(components -> components.getKey().getReportId() == reportId).collect(Collectors.toList());
        HashMap<UsedComponents, String> usedComs = getCompNameAmountMap(used);

        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            //--Header--//
            Font timesBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
            Paragraph head = new Paragraph();
            head.setFont(timesBold);
            head.add("List of used components");
            head.setAlignment(Element.ALIGN_CENTER);
            //--Text--//
            timesBold.setSize(14);
            Chunk chunk = new Chunk("Report: ",timesBold);
            timesBold.setFamily(FontFactory.TIMES);
            chunk.append(reportService.get(reportId).get().getReport()+"\n");
            //--Table--//
            PdfPTable table = new PdfPTable(2);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            Stream.of("Component", "Amount").forEach(s -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setPhrase(new Phrase(s));
                table.addCell(header);
            });

            for (UsedComponents uc : usedComs.keySet()) {
                table.addCell(usedComs.get(uc));
                table.addCell(Integer.toString(uc.getAmount()));
            }
            document.add(head);
            document.add(chunk);
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
