package com.example.kaorurecipe.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorurecipe.Domain.Users;
import com.example.kaorurecipe.Form.LoginForm;
import com.example.kaorurecipe.Service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UsersService service;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public LoginForm setUpLoginForm() {
        LoginForm loginForm = new LoginForm();
        return loginForm;
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        return "/login/login";
    }

    @PostMapping("login")
    public String login(LoginForm form) {
        String email = form.getEmail();
        String password = form.getPassword();

        Users user = service.login(email, password);
        if (user == null) {
            return toLogin();
        } else {
            session.setAttribute("user", user);
            return "redirect:/top";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        session.invalidate();
        return "/login/logout";
    }

}
