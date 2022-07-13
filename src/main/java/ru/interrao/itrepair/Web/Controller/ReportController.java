package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.ElectroComponents.ComponentEnum;
import ru.interrao.itrepair.Web.Entity.Reports.Report;
import ru.interrao.itrepair.Web.Entity.Reports.ReportStatusEnum;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.ReportServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl service;
    @Autowired
    private UserService Userservice;

    @GetMapping("/reports")
    public String defaultPage(Authentication authentication, Model model) {
        User user = Userservice.getUserByUsername(authentication.getName());
        List<Report> list = service.getAll();
        model.addAttribute("listOfTypes", Arrays.asList(ReportStatusEnum.values()));
        model.addAttribute("CurrentUser", user);
        model.addAttribute("CurrentReports", list);
        return "reports/report";
    }

    @PostMapping("/reports")
    public String deleteComponent(@RequestParam(required = true, defaultValue = "") Integer reportId, @RequestParam(required = true, defaultValue = "") ReportStatusEnum action, Model model) {
        Report report =service.get(reportId).get();
        System.out.println(report);
        report.setStatus(action);
        service.save(report);
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
        service.save(report);
        return "redirect:/reports";
    }
}
