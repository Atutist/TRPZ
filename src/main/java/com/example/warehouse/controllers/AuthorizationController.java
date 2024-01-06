package com.example.warehouse.controllers;

import com.example.warehouse.Visitor.ReportingVisitor;
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
public class AuthorizationController {
    private final UserService userService;
    private final AuthorizationService authorizationService;


    //======Login======
    @GetMapping("/login")
    private String login() {
        return "/authorization/login";
    }

    @PostMapping("/login")
    private String loginRequest(Model model,
                                HttpSession session,
                                @RequestParam @NotBlank String username,
                                @RequestParam @NotBlank String password){
        Optional<UserResponse> userResponse = authorizationService.checkUser(username, password);
        if (userResponse.isEmpty()) {
            model.addAttribute("failMessage", "Неправильний логін або пароль");
            model.addAttribute("username", username);
            return "/authorization/login";
        }
        session.setAttribute("user", userResponse.get());
        UserResponse currentUser = (UserResponse) session.getAttribute("user");
        User user = userService.findById(currentUser.getId());
        ReportingVisitor visitor = new ReportingVisitor();
        user.accept(visitor);
        return determineRedirect(userResponse.get().getRole());

    }


    private String determineRedirect(Role role) {
        return switch (role) {
            case ADMIN -> "redirect:/admin/adminHome";
            case WORKER -> "redirect:/worker/workerHome";
            case BOSS -> "redirect:/boss/bossHome";
        };
    }

    //===ErrorPage===

    @GetMapping("/error")
    private String errorPage(){ return "/errors/errorPage";
    }

    //===Logout===

    @PostMapping("/logout")
    private String logout(HttpSession session) {
        if (session.getAttribute("user") != null) {
            UserResponse currentUser = (UserResponse) session.getAttribute("user");
            User user = userService.findById(currentUser.getId());
            ReportingVisitor visitor = new ReportingVisitor();
            visitor.visitUserLogout(user);
        }
        session.removeAttribute("user");

        return "redirect:/";
    }
}
