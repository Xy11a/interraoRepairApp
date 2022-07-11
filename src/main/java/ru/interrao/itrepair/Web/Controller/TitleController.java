package ru.interrao.itrepair.Web.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.interrao.itrepair.Web.Entity.Auth.User;

import java.security.Principal;

@Controller
public class TitleController {

    @RequestMapping(value = "/" )
    public String home( Model model)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        model.addAttribute("CurrentUser",user);
        return "home/start";
    }
}
