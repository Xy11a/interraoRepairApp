package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.Reports.Report;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.ReportServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class TitleController {

    @Autowired
    private UserService Userservice;

    @Autowired
    private ReportServiceImpl reportService;
    @RequestMapping(value = "/" )
    public String home(Authentication authentication, Model model)
    {
        User user = Userservice.getUserByUsername(authentication.getName());
        List<Report> reports = reportService.getAll().stream().filter(report -> report.getOwner().equals(user.getUsername())).collect(Collectors.toList());

        System.out.println(reports);
        model.addAttribute("CurrentUser",user);
        model.addAttribute("CurrentReports",reports);
        return "home/start";
    }

    @RequestMapping(value = "/login-fail")
    public String homeError(Model model)
    {
        model.addAttribute("LoginError", true);
        return "home/index";
    }
}
