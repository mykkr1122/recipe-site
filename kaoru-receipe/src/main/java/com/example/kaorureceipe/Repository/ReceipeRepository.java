package com.example.kaorureceipe.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.kaorureceipe.Domain.Receipe;

@Repository
public class ReceipeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Receipe> RECEIPE_ROW_MAPPER = (rs, i) -> {
        Receipe receipe = new Receipe(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("introduction"),
                rs.getInt("serving"),
                rs.getString("ingredients"),
                rs.getString("detail"),
                rs.getString("point"),
                rs.getString("image_path"),
                rs.getBoolean("display_flag"));
        return receipe;
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
    public Receipe load(Integer id) {
        String sql = """
                SELECT *
                  FROM receipe
                 WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Receipe receipe = template.queryForObject(sql, param, RECEIPE_ROW_MAPPER);
        return receipe;
    }

    /**
     * 料理名から検索
     * 
     * @param title
     * @return
     */
    public Receipe findByTitle(String title) {
        String sql = """
                SELECT *
                  FROM receipe
                 WHERE title = :title;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("title", title);
        Receipe receipe = template.queryForObject(sql, param, RECEIPE_ROW_MAPPER);
        return receipe;
    }

    /**
     * すべての料理名のリストを返す
     * 
     * @return
     */
    public List<String> getTitleList() {
        String sql = """
                SELECT title
                  FROM receipe
                 WHERE display_flag = false;
                """;
        List<String> titleList = template.query(sql, TITLE_ROW_MAPPER);
        return titleList;
    }

    public int countReceipe() {

        String sql = """
                SELECT COUNT(*)
                  FROM receipe;
                """;

        return template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    /**
     * 全検索
     * 
     * @return
     */
    public List<Receipe> findAll() {
        String sql = """
                SELECT id, title, introduction, serving, ingredients, detail, point, image_path, display_flag
                  FROM receipe
                 WHERE display_flag = false;
                """;
        List<Receipe> receipes = template.query(sql, RECEIPE_ROW_MAPPER);
        return receipes;
    }

    /**
     * レシピの登録
     * 
     * @param receipe
     */
    public void insert(Receipe receipe) {
        String sql = """
                INSERT INTO receipe (title, introduction, serving, ingredients, detail, point, image_path)
                VALUES (:title, :introduction, :serving, :ingredients, :detail, :point, :imagePath);
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(receipe);
        template.update(sql, param);
    }

    /**
     * レシピを論理削除する（display_flagを false から true にする）
     * 
     * @param id
     */
    public void updateDisplayFlagById(Integer id) {
        String sql = """
                UPDATE receipe
                   SET display_flag = true
                 WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public List<Receipe> findReceipesWithPagination(int offset, int limit) {
        String sql = """
                SELECT id, title, introduction, serving, ingredients, detail, point, image_path, display_flag
                 FROM receipe
                 WHERE display_flag = false
                 LIMIT :limit OFFSET :offset;
                """;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("limit", limit);
        param.addValue("offset", offset);

        return template.query(sql, param, RECEIPE_ROW_MAPPER);
    }

}
