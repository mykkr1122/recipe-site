package com.example.kaorurecipe.Contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kaorurecipe.Domain.Recipe;
import com.example.kaorurecipe.Form.RecipeEditForm;
import com.example.kaorurecipe.Service.RecipeService;

@Controller
@RequestMapping("")
public class RecipeController {
    @Autowired
    private RecipeService service;

    /**
     * トップページ レシピ一覧画面
     * 
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("top")
    public String index(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size, Model model) {

        int offset = page * size;
        int totalRecipe = service.countRecipe(); // レシピの総数
        int totalPage = (int) Math.ceil((double) totalRecipe / size); // ページ総数

        // 現在のページが範囲外であれば最後のページに調整
        if (page >= totalPage) {
            page = totalPage - 1;
            offset = page * size;
        }

        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(0, page - 2); // 現在のページの前2ページからスタート
        int end = Math.min(totalPage, page + 3); // 現在のページの後3ページまで

        // ページ番号リストの生成
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

    /**
     * レシピ詳細ページ繊維
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public String detail(Integer id, Model model) {
        Recipe recipe = service.load(id);
        model.addAttribute("recipe", recipe);

        return "/recipe/detail";
    }

    /**
     * レシピの論理削除
     * 
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String deleteRecipe(Integer id) {
        service.deleteRecipe(id);

        return "redirect:/top";
    }

    /**
     * レシピ編集画面表示
     * 
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public String ToEdit(Integer id, Model model) {
        Recipe recipe = service.load(id);

        RecipeEditForm form = new RecipeEditForm();
        form.setId(recipe.getId());
        form.setTitle(recipe.getTitle());
        form.setIntroduction(recipe.getIntroduction());
        form.setServing(recipe.getServing());
        form.setIngredients(recipe.getIngredients());
        form.setDetail(recipe.getDetail());
        form.setPoint(recipe.getPoint());
        form.setImagePath(recipe.getImagePath());

        model.addAttribute("recipeEditForm", form);

        return "/recipe/edit";
    }

    /**
     * レシピ編集
     * 
     * @param id
     * @param form
     * @return
     */
    @PostMapping("edit")
    public String editRecipe(RecipeEditForm form) {
        Recipe recipe = new Recipe();
        recipe.setId(form.getId());
        recipe.setTitle(form.getTitle());
        recipe.setIntroduction(form.getIntroduction());
        recipe.setServing(form.getServing());
        recipe.setIngredients(form.getIngredients());
        recipe.setDetail(form.getDetail());
        recipe.setPoint(form.getPoint());
        recipe.setImagePath(form.getImagePath());

        service.editRecipe(form);

        return "redirect:/top";
    }

}
