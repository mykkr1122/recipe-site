package com.example.kaorurecipe.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorurecipe.Form.RecipeResisterForm;
import com.example.kaorurecipe.Service.RecipeService;

@Controller
@RequestMapping("")
public class InsertRecipeInfoController {
    @Autowired
    private RecipeService service;

    @ModelAttribute
    public RecipeResisterForm setUpRecipeResisterForm(){
        RecipeResisterForm recipeResisterForm = new RecipeResisterForm();
        return recipeResisterForm;
    }

    @RequestMapping("recipeResister")
    public String recipeResister() {
        return "recipe/resister";
    }

    @PostMapping("/saveRecipe")
    public String saveRecipe(RecipeResisterForm form) {
        
        service.saveRecipe(form);

        return "recipe/completation";
    }
}
