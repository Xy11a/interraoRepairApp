package ru.interrao.itrepair.Web.Controller;

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
import ru.interrao.itrepair.Web.Repository.ComponentRepository;
import ru.interrao.itrepair.Web.Services.ComponentService;
import ru.interrao.itrepair.Web.Services.ReportService;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.ReportServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UsedComponentServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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
        User user = Userservice.getUserByUsername(authentication.getName());

        List<UsedComponents> usedComs = usedComponentService.getAll();
        List<Integer> usedComsId = new ArrayList<>();

        usedComs.forEach(c -> usedComsId.add(c.getKey().getComponentId()));

        List<Component> compOptions = componentService.getAll();

        compOptions.removeIf(c -> usedComsId.contains(c.getId()));

        UsedComponents newComp =  new UsedComponents();
        newComp.setKey(new UsedComponentsPK());
        newComp.getKey().setReportId(reportId);
        newComp.setAmount(0);

        HashMap<UsedComponents, String> hashMap = new HashMap<>();
        for (UsedComponents comp : usedComs)
            hashMap.put(comp, componentService.get(comp.getKey().getComponentId()).get().getName());


        model.addAttribute("CurrentUser", user);
        model.addAttribute("CurrentsComps", hashMap);
        model.addAttribute("options", compOptions);
        model.addAttribute("newComp",newComp);


        return "usedComponent/index";
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
        return "redirect:/reports/used/"+reportId;
    }

    @PostMapping("/reports/used/{id}/create")
    public String saveComp(@PathVariable(name = "id") int reportId, @ModelAttribute("newComp") @Valid UsedComponents component)
    {
        System.out.println("Sad Cat");
        System.out.println(component.getKey());
        component.getKey().setReportId(reportId);
        usedComponentService.save(component);
        return "redirect:/reports/used/"+reportId;
    }


}
