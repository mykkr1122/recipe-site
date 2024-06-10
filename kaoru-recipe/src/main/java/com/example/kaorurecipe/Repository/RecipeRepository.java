package com.example.kaorurecipe.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.kaorurecipe.Domain.Recipe;

@Repository
public class RecipeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Recipe> RECIPE_ROW_MAPPER = (rs, i) -> {
        Recipe recipe = new Recipe(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("introduction"),
                rs.getInt("serving"),
                rs.getString("ingredients"),
                rs.getString("detail"),
                rs.getString("point"),
                rs.getString("image_path"),
                rs.getBoolean("display_flag"));
        return recipe;
    };

    private static final RowMapper<String> TITLE_ROW_MAPPER = (rs, i) -> {
        String title = rs.getString("title");
        return title;
    };

    /**
     * idから検索
     * 
     * @param id
     * @return
     */
    public Recipe load(Integer id) {
        String sql = """
                SELECT *
                  FROM recipe
                 WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Recipe recipe = template.queryForObject(sql, param, RECIPE_ROW_MAPPER);
        return recipe;
    }

    /**
     * 料理名から検索
     * 
     * @param title
     * @return
     */
    public Recipe findByTitle(String title) {
        String sql = """
                SELECT *
                  FROM recipe
                 WHERE title = :title;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("title", title);
        Recipe recipe = template.queryForObject(sql, param, RECIPE_ROW_MAPPER);
        return recipe;
    }

    /**
     * すべての料理名のリストを返す
     * 
     * @return
     */
    public List<String> getTitleList() {
        String sql = """
                SELECT title
                  FROM recipe
                 WHERE display_flag = false;
                """;
        List<String> titleList = template.query(sql, TITLE_ROW_MAPPER);
        return titleList;
    }

    public int countRecipe() {

        String sql = """
                SELECT COUNT(*)
                  FROM recipe;
                """;

        return template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    /**
     * 全検索
     * 
     * @return
     */
    public List<Recipe> findAll() {
        String sql = """
                SELECT id, title, introduction, serving, ingredients, detail, point, image_path, display_flag
                  FROM recipe
                 WHERE display_flag = false;
                """;
        List<Recipe> recipes = template.query(sql, RECIPE_ROW_MAPPER);
        return recipes;
    }

    /**
     * レシピの登録
     * 
     * @param recipe
     */
    public void insert(Recipe recipe) {
        String sql = """
                INSERT INTO recipe (title, introduction, serving, ingredients, detail, point, image_path)
                VALUES (:title, :introduction, :serving, :ingredients, :detail, :point, :imagePath);
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(recipe);
        template.update(sql, param);
    }

    /**
     * レシピを論理削除する（display_flagを false から true にする）
     * 
     * @param id
     */
    public void updateDisplayFlagById(Integer id) {
        String sql = """
                UPDATE recipe
                   SET display_flag = true
                 WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public List<Recipe> findRecipesWithPagination(int offset, int limit) {
        String sql = """
                SELECT id, title, introduction, serving, ingredients, detail, point, image_path, display_flag
                 FROM recipe
                 WHERE display_flag = false
                 LIMIT :limit OFFSET :offset;
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("limit", limit);
        param.addValue("offset", offset);

        return template.query(sql, param, RECIPE_ROW_MAPPER);
    }

}
