package com.example.warehouse.controllers;

import com.example.warehouse.entity.User;
import org.springframework.ui.Model;
import com.example.warehouse.dto.UserResponse;
import com.example.warehouse.enums.Role;
import com.example.warehouse.services.AuthorizationService;
import com.example.warehouse.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    private final AuthorizationService authorizationService;



    //===Pages for each user====
    @GetMapping("/admin/adminHome")
    private String adminHome(HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.ADMIN))){
            return "redirect:/accessDenied";
        }
        return "/admin/adminHome";
    }

    @GetMapping("/accessDenied")
    private String accessDenied(){ return "/errors/accessDenied";   }

    @GetMapping("/worker/workerHome")
    private String workerHome(HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.WORKER))){
            return "redirect:/accessDenied";
        }
        return "/worker/workerHome";
    }

    @GetMapping("/boss/bossHome")
    private String bossHome(HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.BOSS))){
            return "redirect:/accessDenied";
        }
        return "/boss/bossHome";
    }

    //=====Operation with users======
    @GetMapping("/admin/registration")
    private String registration(Model model, HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.ADMIN))){
            return "redirect:/accessDenied";
        }
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin/registration";
    }

    @GetMapping("/admin/operationWithUser")
    private String operationWithUser(Model model, HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.ADMIN))){
            return "redirect:/accessDenied";
        }
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin/operationWithUser";
    }

    @PostMapping("/admin/register")
    private String registerRequest(Model model,
                                   @RequestParam @NotBlank String username,
                                   @RequestParam @NotBlank String password,
                                   @RequestParam @NotBlank String reppassword,
                                   @RequestParam Role role,
                                   HttpSession session){
        UserResponse user = (UserResponse) session.getAttribute("user");
        if( (user == null || !user.getRole().equals(Role.ADMIN))){
            return "redirect:/accessDenied";
        }
        //====validation===
        try {
            if (!password.equals(reppassword)){
                model.addAttribute("failMessage","Passwords are not same");
                model.addAttribute("username", username);
                return "/admin/registration";
            }
        }catch ( Exception e){
            model.addAttribute("failMessage", e.getMessage());
            model.addAttribute("username", username);
            return "/admin/registration";
        }
        //====is name available====
        if(authorizationService.isUserExist(username)){
            model.addAttribute("failMessage", "You already have this username");
            model.addAttribute("username", username);
            return "/admin/registration";
        }
        //====All good====
        userService.createUser(username, password, role);
        model.addAttribute("message", "User was created");
        return "/admin/registration";
    }

    //===delete user===
    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId, HttpSession session, Model model) {
        UserResponse userResponse = (UserResponse) session.getAttribute("user");
        if( (userResponse == null || !userResponse.getRole().equals(Role.ADMIN))){
            return "redirect:/accessDenied";
        }
        if (userResponse.getId() == userId) {
            model.addAttribute("errorMessage", "Cannot delete currently logged-in user.");
        } else {
            userService.deleteUser(userId);
        }
        return "redirect:/admin/operationWithUser";
    }
}
