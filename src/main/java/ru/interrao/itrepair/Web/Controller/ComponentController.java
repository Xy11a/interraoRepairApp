package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;
import ru.interrao.itrepair.Web.Entity.ElectroComponents.ComponentEnum;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.ComponentServiceImpl;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import java.util.Arrays;

@Controller
public class ComponentController {
    @Autowired
    ComponentServiceImpl service;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/components", method = RequestMethod.GET)
    public String defaultPage(Authentication authentication, Model model) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser",user);
        model.addAttribute("CurrentReports", service.getAll());
        System.out.println(service.getAll());
        return "components/CompPage";
    }

    @PostMapping("/components")
    public String deleteComponent(@RequestParam(required = true, defaultValue = "") Integer userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) service.deleteById(userId);
        return "redirect:/components";
    }

    @GetMapping("/components/new")
    public String createComponent(Model model) {
        Component comp = new Component();
        model.addAttribute("ComponentObj", comp);
        model.addAttribute("listOfTypes", Arrays.asList(ComponentEnum.values()));
        return "components/NewCompPage";
    }

    @PostMapping("/components/new")
    public String submitForm(@ModelAttribute("ComponentObj") Component comp) {
        System.out.println(comp);
        service.save(comp);
        return "redirect:/components";
    }


    @GetMapping("/components/update")
    public String updatePage(@RequestParam(required = true, defaultValue = "") Integer compId, Model model) {
        model.addAttribute("updateComp", service.get(compId));
        return "components/CompUpdate";
    }


    @PostMapping("/components/update")
    public String updateReq(@ModelAttribute("updateComp") Component comp, Model model) {
        System.out.println(comp);
        service.update(comp);
        return "redirect:/components";
    }
}

