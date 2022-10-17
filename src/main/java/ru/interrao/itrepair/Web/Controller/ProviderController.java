package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;
import ru.interrao.itrepair.Web.Entity.Provider.Provider;
import ru.interrao.itrepair.Web.Services.ProviderService;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;

import javax.validation.Valid;

@Controller
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/provider")
    public String providerGet(Model model, Authentication authentication)
    {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser",user);
        model.addAttribute("CurrentProviders", providerService.getAll());

        return "provider/index";
    }

    @PostMapping(value = "/provider")
    public String deleteProvider(@RequestParam(required = true, defaultValue = "") Integer providerId, @RequestParam(required = true, defaultValue = "") String action, Model model)
    {
        if (action.equals("delete")) providerService.deleteById(providerId);
        return "redirect:/provider";
    }

    @GetMapping(value = "/provider/new")
    public String providerNew(Model model, Authentication authentication)
    {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser",user);
        model.addAttribute("newProvider", new Provider());
        return "provider/create";
    }

    @PostMapping(value = "/provider/new")
    public String addProvider(@ModelAttribute("newProvider") @Valid Provider provider, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "provider/new";
        else providerService.save(provider);
        return "redirect:/provider";
    }


    @GetMapping(value = "/provider/update")
    public String provider(@RequestParam(required = true, defaultValue = "") Integer providerId, Model model,Authentication authentication)
    {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser",user);
        model.addAttribute("curProvider", providerService.get(providerId));
        return "provider/update";
    }

    @PostMapping("/provider/update")
    public String updateReq(@ModelAttribute("curProvider") Provider provider, Model model) {
        System.out.println(provider);
        providerService.update(provider);
        return "redirect:/provider";
    }
}
