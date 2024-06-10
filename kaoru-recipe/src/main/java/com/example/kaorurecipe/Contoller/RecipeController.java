package com.example.kaorurecipe.Contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kaorurecipe.Domain.Recipe;
import com.example.kaorurecipe.Service.RecipeService;

@Controller
@RequestMapping("")
public class RecipeController {
    @Autowired
    private RecipeService service;

    @RequestMapping("top")
    public String index(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size, Model model) {

        int offset = page * size;
        int totalRecipe = service.countRecipe();
        int totalPage = (int) Math.ceil((double) totalRecipe / size);
        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(0, page - 2);
        int end = Math.min(totalPage, page + 3);
        for (int i = start; i < end; i++) {
            pageNumbers.add(i);
        }
        List<Recipe> recipes = service.findRecipesWithPagination(offset, size);
        model.addAttribute("recipes", recipes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("pageSize", size);
        return "/recipe/top";
    }

    @RequestMapping("detail")
    public String detail(Integer id, Model model) {
        Recipe recipe = service.load(id);
        model.addAttribute("recipe", recipe);

        return "/recipe/detail";
    }

    @RequestMapping("")
    public String deleteRecipe(Integer id) {
        service.deleteRecipe(id);

        return "redirect:/top";
    }

}
