package com.example.kaorureceipe.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.kaorureceipe.Domain.Users;

/**
 * Usersテーブルを扱うリポジトリ
 */
@Repository
public class UsersRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Users> USER_ROW_MAPPER = (rs, i) -> {
        Users user = new Users(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"));
        return user;
    };

    /**
     * idから検索
     * @param id
     * @return
     */
    public Users load(Integer id) {
        String sql = """
                SELECT * FROM users
                 WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Users user = template.queryForObject(sql, param, USER_ROW_MAPPER);
        return user;
    }

    /**
     * メールアドレスとパスワードから検索
     * @param email
     * @param password
     * @return
     */
    public Users login(String email, String password) {
        String sql = """
                SELECT * FROM users
                 WHERE email = :email AND password =:password;
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email", email).addValue("password", password);
        Users user = template.queryForObject(sql, param, USER_ROW_MAPPER);
        return user;
    }
    

    /**
     * ユーザー登録
     * @param user
     */
    public void insert(Users user){
        String sql = """
                INSERT INTO users (name, email, password)
                VALUES (:name, :email, :password);
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        template.update(sql, param);
       
    }
}
