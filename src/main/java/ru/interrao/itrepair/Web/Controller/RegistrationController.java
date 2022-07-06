package ru.interrao.itrepair.Web.Controller;

import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Services.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()) return "home/registration";
        System.out.println(userForm);
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm()))
        {
            model.addAttribute("passwordError","Пароли не совпадают");
            return  "home/registration";
        }
        if(!userService.saveUser(userForm)){
            model.addAttribute("usernameError","Пользователь с таким именем уже существует");
            return "home/registration";
        }
        return ":redirect:/";
    }
}
