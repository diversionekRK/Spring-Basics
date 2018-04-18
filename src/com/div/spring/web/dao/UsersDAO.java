package com.div.spring.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
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
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    @Transactional
    public boolean create(User user) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);

        jdbc.update("insert into users (username, password, email, enabled) values (:username, :password, :email, :enabled)", params);

        return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params) == 1;
    }

    public boolean exists(String username) {
        MapSqlParameterSource param = new MapSqlParameterSource("username", username);

        return jdbc.queryForObject("select count(*) from users where username = :username", param, Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from users natural join authorities", new BeanPropertyRowMapper(User.class) );
    }
}
