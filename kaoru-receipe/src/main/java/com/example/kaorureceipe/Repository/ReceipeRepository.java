package com.example.kaorureceipe.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.kaorureceipe.Domain.Receipe;

@Repository
public class ReceipeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Receipe> RECEIPE_ROW_MAPPER = (rs,i)->{
        Receipe receipe = new Receipe(
            rs.getInt("id"),
            rs.getString("title") ,           
            rs.getString("detail") ,           
            rs.getString("ingredient") ,           
            rs.getString("image_path") ,           
            rs.getBoolean("display_flag")       
        );
        // receipe.setId(rs.getInt("id"));
        // receipe.setTitle(rs.getString("title"));
        // receipe.setDetail(rs.getString("detail"));
        // receipe.setIngredient(rs.getString("ingredient"));
        // receipe.setImagePath(rs.getString("image_path"));
        // receipe.setDisplayFlag(rs.getBoolean("display_flag"));
        return receipe;
    };

    public Receipe load(Integer id){
        String sql= """
                SELECT * FROM receipe WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Receipe receipe = template.queryForObject(sql, param, RECEIPE_ROW_MAPPER);
        return receipe;
    }

    
}
