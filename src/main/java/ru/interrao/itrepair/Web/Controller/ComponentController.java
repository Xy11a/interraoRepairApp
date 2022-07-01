package ru.interrao.itrepair.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Services.Impl.ComponentServiceImpl;

@Controller
public class ComponentController
{
    @Autowired
    ComponentServiceImpl service;

   @RequestMapping(value ="/components/", method = RequestMethod.GET)
    public String defaultPage(Model model)
   {
       model.addAttribute("allComponents", service.getAll());
       return "CompPage";
   }

    @PostMapping("/components/")
    public String deleteUser(@RequestParam(required = true,defaultValue = "") Integer userId, @RequestParam(required = true,defaultValue = "") String action, Model model)
    {
        if(action.equals("delete")) service.deleteById(userId);
        return "redirect:/components/";
    }
}

