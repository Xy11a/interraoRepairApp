package ru.interrao.itrepair.Web.Controller;

import org.springframework.security.core.Authentication;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model, Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser",user);

        model.addAttribute("allUsers", userService.allUsers());
        return "/adminstation/admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Integer userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
}
