package com.example.kaorurecipe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kaorurecipe.Domain.Recipe;
import com.example.kaorurecipe.Form.RecipeResisterForm;
import com.example.kaorurecipe.Repository.RecipeRepository;

@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    public Recipe load(Integer id) {
        Recipe recipe = repository.load(id);
        return recipe;
    }

    public Recipe findByTitle(String title) {
        Recipe recipe = repository.findByTitle(title);
        return recipe;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = repository.findAll();
        return recipes;
    }

    public void saveRecipe(RecipeResisterForm form) {
        Recipe recipe = new Recipe();
        recipe.setTitle(form.getTitle());
        recipe.setIntroduction(form.getIntroduction());
        recipe.setServing(form.getServing());
        recipe.setIngredients(form.getIngredients());
        recipe.setDetail(form.getDetail());
        recipe.setPoint(form.getPoint());
        recipe.setImagePath(form.getImagePath());
        repository.insert(recipe);
    }

    public void deleteRecipe(Integer id) {
        repository.updateDisplayFlagById(id);
    }

    public List<Recipe> findRecipesWithPagination(int offset, int limit) {
        return repository.findRecipesWithPagination(offset, limit);
    }

    public int countRecipe() {
        return repository.countRecipe();
    }
}
