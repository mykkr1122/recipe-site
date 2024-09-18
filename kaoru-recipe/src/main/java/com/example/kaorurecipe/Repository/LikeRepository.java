package com.example.kaorurecipe.Repository;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.kaorurecipe.Domain.Like;

@Repository
public class LikeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Like> LIKE_ROW_MAPPER = (rs, i) -> {
        Like like = new Like(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("recipe_id"),
                rs.getBoolean("display_flag"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
        return like;
    };

    /**
     * 1件のいいねを取得する
     * 
     * @param userId
     * @param recipeId
     * @return
     */
    public Like find(Integer userId, Integer recipeId) {
        String sql = """
                SELECT *
                  FROM likes
                 WHERE user_id = :userId
                   AND recipe_id = :recipeId
                   AND display_flag = true;
                """;
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId)
                .addValue("recipeId", recipeId);

        return template.queryForObject(sql, param, LIKE_ROW_MAPPER);
    }

    /**
     * いいねを挿入する
     * 
     * @param userId
     * @param recipeId
     */
    public void insert(Integer userId, Integer recipeId) {
        String sql = """
                INSERT INTO likes (user_id, recipe_id, created_at)
                VALUES (:userId, :recipeId, CURRENT_TIMESTAMP);
                """;
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId)
                .addValue("recipeId", recipeId);

        template.update(sql, param);
    }

    /**
     * いいねを更新する
     * 
     * @param userId
     * @param recipeId
     * @param displayFlag
     */
    public void update(Integer userId, Integer recipeId, boolean displayFlag) {
        String sql = """
                UPDATE likes
                   SET display_flag = :displayFlag,
                       updated_at = CURRENT_TIMESTAMP
                 WHERE user_id = :userId
                   AND recipe_id = :recipeId;
                """;
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("recipeId", recipeId)
                .addValue("displayFlag", displayFlag);
        template.update(sql, param);
    }

    /**
     * いいねを論理削除する
     * 
     * @param userId
     * @param recipeId
     * @param displayFlag
     */
    public void delete(Integer userId, Integer recipeId, boolean displayFlag) {
        String sql = """
                UPDATE likes
                   SET display_flag = :displayFlag,
                 WHERE user_id = :userId
                   AND recipe_id = :recipeId;
                """;
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("recipeId", recipeId)
                .addValue("displayFlag", displayFlag);
        template.update(sql, param);
    }

    /**
     * いいね数をカウントする
     * 
     * @param recipeId
     * @return
     */
    public Integer countLikesByRecipeId(Integer recipeId) {
        String sql = """
                SELECT COUNT(*)
                  FROM likes
                WHERE recipe_id = :recipeId
                  AND display_flag = true;
                """;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("recipeId", recipeId);

        return template.queryForObject(sql, param, Integer.class);
    }
}
