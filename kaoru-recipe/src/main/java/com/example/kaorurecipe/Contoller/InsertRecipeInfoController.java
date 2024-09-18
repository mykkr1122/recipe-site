package com.example.kaorurecipe.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kaorurecipe.Domain.Users;
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

    /**
     * レシピ登録画面を表示
     * 
     * @return
     */
    @RequestMapping("recipeResister")
    public String recipeResister() {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "/login/login";
        } else {
            return "recipe/resister";
        }
    }

    /**
     * レシピ登録
     * 
     * @param form
     * @return
     */
    @PostMapping("/saveRecipe")
    public String saveRecipe(RecipeResisterForm form) {

        service.saveRecipe(form);

        return "recipe/completation";
    }
}
