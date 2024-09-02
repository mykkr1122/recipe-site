package com.example.kaorurecipe.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorurecipe.Form.RecipeResisterForm;
import com.example.kaorurecipe.Service.RecipeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class InsertRecipeInfoController {
    @Autowired
    private RecipeService service;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public RecipeResisterForm setUpRecipeResisterForm() {
        RecipeResisterForm recipeResisterForm = new RecipeResisterForm();
        return recipeResisterForm;
    }

    @RequestMapping("recipeResister")
    public String recipeResister() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "/login/login";
        } else {
            return "recipe/resister";
        }
    }

    @PostMapping("/saveRecipe")
    public String saveRecipe(RecipeResisterForm form) {

        service.saveRecipe(form);

        return "recipe/completation";
    }
}
