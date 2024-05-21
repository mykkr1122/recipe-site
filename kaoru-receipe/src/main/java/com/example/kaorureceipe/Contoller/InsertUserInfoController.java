package com.example.kaorureceipe.Contoller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorureceipe.Domain.Users;
import com.example.kaorureceipe.Form.UserResisterForm;
import com.example.kaorureceipe.Service.UsersService;

@Controller
@RequestMapping("")
public class InsertUserInfoController {

    @ModelAttribute
    public UserResisterForm setUpInsertUserInfoForm(){
        UserResisterForm userResisterForm = new UserResisterForm();
        return userResisterForm;
    }

    @Autowired
    private UsersService service;

    @RequestMapping("usrResister")
    public String userResister(){
        return "usr/user-resister";
    }

    @PostMapping("insertUserInfo")
    public String inserUserInfo(UserResisterForm form){
        Users user = new Users();
        BeanUtils.copyProperties(form, user);
        service.saveUser(user);
        
        return "redirect:/toLogin";
    }
}
