package ru.interrao.itrepair.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Random;

@Controller
public class TitleController {
    @RequestMapping(value = "/" )
    public String loadPage(Model model)
    {
        return "home";
    }
}
