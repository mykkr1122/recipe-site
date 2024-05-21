package com.example.kaorureceipe.Contoller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorureceipe.Domain.Receipe;
import com.example.kaorureceipe.Form.ReceipeResisterForm;
import com.example.kaorureceipe.Service.ReceipeService;

@Controller
@RequestMapping("")
public class InsertReceipeInfoController {
    @Autowired
    private ReceipeService service;

    @ModelAttribute
    public ReceipeResisterForm setUpReceipeResisterForm(){
        ReceipeResisterForm receipeResisterForm = new ReceipeResisterForm();
        return receipeResisterForm;
    }

    @RequestMapping("receipeResister")
    public String receipeResister() {
        return "receipe/resister";
    }

    @PostMapping("/saveReceipe")
    public String saveReceipe(ReceipeResisterForm form) {
        Receipe receipe = new Receipe();
        BeanUtils.copyProperties(form, receipe);

        service.saveReceipe(receipe);

        return "receipe/completation";
    }
}
