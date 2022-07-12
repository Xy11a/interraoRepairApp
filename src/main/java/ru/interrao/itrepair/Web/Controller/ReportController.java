package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.Reports.Report;
import ru.interrao.itrepair.Web.Entity.Reports.ReportStatusEnum;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.ReportServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl service;
    @Autowired
    private UserService Userservice;

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String defaultPage(Authentication authentication, Model model) {
        User user = Userservice.getUserByUsername(authentication.getName());
        List<Report> list = service.getAll();
        model.addAttribute("CurrentUser",user);
        model.addAttribute("CurrentReports", list);
        return "reports/report";
    }

    @PostMapping("/reports")
    public String deleteComponent(@RequestParam(required = true, defaultValue = "") Integer userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) service.deleteById(userId);
        return "redirect:/reports";
    }

    @GetMapping("/reports/new")
    public String createComponent(Model model) {
        Report report = new Report();
        model.addAttribute("ReportObj", report);
        model.addAttribute("listOfStatus", Arrays.asList(ReportStatusEnum.values()));
        return "reports/newReport";
    }

    @PostMapping("/reports/new")
    public String submitForm(@ModelAttribute("ComponentObj") Report report) {
        System.out.println(report);
        service.save(report);
        return "redirect:/reports";
    }


    @GetMapping("/reports/update")
    public String updatePage(@RequestParam(required = true, defaultValue = "") Integer compId, Model model) {
        model.addAttribute("updateComp", service.get(compId));
        return "components/reportUpdate";
    }


    @PostMapping("/reports/update")
    public String updateReq(@ModelAttribute("updateComp") Report report, Model model) {
        System.out.println(report);
        service.update(report);
        return "redirect:/components";
    }
}
