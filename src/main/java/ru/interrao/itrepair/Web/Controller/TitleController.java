package ru.interrao.itrepair.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TitleController {

    @RequestMapping(value = "/" )
    public String home()
    {
        return "redirect:/login";
    }
}
