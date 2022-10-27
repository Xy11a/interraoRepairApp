package ru.interrao.itrepair.Web.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.interrao.itrepair.Web.Entity.Auth.Role;
import ru.interrao.itrepair.Web.Entity.Auth.User;
import ru.interrao.itrepair.Web.Entity.CompositeEntity.UsedComponents;
import ru.interrao.itrepair.Web.Repository.RoleRepository;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.RoleService;
import ru.interrao.itrepair.Web.Services.ServiceImplementations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin")
    public String userList(Model model, Authentication authentication) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser", user);

        model.addAttribute("allUsers", userService.allUsers());

        return "/adminstation/admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Integer userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) userService.deleteUser(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String editUser(@PathVariable(name = "id") int userId, Authentication authentication, Model model) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("CurrentUser", user);

        List<Role> userRoles = new ArrayList<>();
        userRoles.addAll(userService.findUserById(userId).getRoles());

        List<Role> allRoles = roleRepository.findAll();

        List<Role> availableRoles = new ArrayList<>();
        allRoles.forEach(roleAll ->
        {
            if (!userRoles.contains(roleAll)) availableRoles.add(roleAll);
        });

        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("availableRoles", availableRoles);
        return "/adminstation/edit";
    }

    @PostMapping("/admin/edit-user/{id}")
    public String updateUsedComp(@PathVariable(name = "id") int userId, @RequestParam(name = "roleId") int rmRole, @RequestParam(name = "action") String action) {
        switch (action) {
            case "del":
                User user = userService.findUserById(userId);
                Set<Role> roleSet = user.getRoles();
                user.setRoles(roleSet.stream().filter(role -> role.getId() != rmRole).collect(Collectors.toSet()));
                userService.updateUser(user);
                break;
        }
        return "redirect:/admin/edit-user/" + userId;
    }


    @PostMapping("/admin/edit-user/{id}/create")
    public String saveComp(@PathVariable(name = "id") int userId, @RequestParam(name = "select") int newRoleId) {
        System.out.println("Sad");

        Set<Role> currentRoles = new HashSet<>();
        currentRoles.addAll(userService.findUserById(userId).getRoles());
        currentRoles.add(roleRepository.findById(newRoleId).get());

        User user = userService.findUserById(userId);
        user.setRoles(currentRoles);
        userService.updateUser(user);


        return "redirect:/admin/edit-user/" + userId;
    }
}
