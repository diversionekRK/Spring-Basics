package com.div.spring.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Div on 2018-02-13.
 */

@Component
public class UsersDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    @Transactional
    public boolean create(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("username", user.getUsername());
        params.addValue("password", passwordEncoder.encode(user.getPassword()));
        params.addValue("email", user.getEmail());
        params.addValue("name", user.getName());
        params.addValue("enabled", user.isEnabled());
        params.addValue("authority", user.getAuthority());

        return jdbc.update(
                "insert into users (username, password, email, name, enabled, authority) " +
                        "values (:username, :password, :email, :name, :enabled, :authority)", params
        ) == 1;
    }

    public boolean exists(String username) {
        MapSqlParameterSource param = new MapSqlParameterSource("username", username);

        return jdbc.queryForObject("select count(*) from users where username = :username", param, Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from users", new BeanPropertyRowMapper(User.class));
    }
}
