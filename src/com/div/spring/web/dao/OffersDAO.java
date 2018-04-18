package com.div.spring.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OffersDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean insert(String name, String email, String text) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("email", email);
        params.addValue("text", text);

        return jdbc.update("insert into offers values (null, :name, :email, :text)", params) == 1;
    }

    public boolean create(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("insert into offers (name, email, text) values (:name, :email, :text)", params) == 1;
    }

    @Transactional
    public int[] create(List<Offer> offers) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());

        return jdbc.batchUpdate("insert into offers (id, name, email, text) values(:id, :name, :email, :text)", params);
    }

    public boolean update(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("update offers set name = :name, email = :email, text = :text where id = :id", params) == 1;
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.update("delete from offers where id = :id", params) == 1;
    }

    public List<Offer> getOffers() {
        return jdbc.query("select * from offers", new RowMapper<Offer>() {
            public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
                Offer offer = new Offer();

                offer.setId(resultSet.getInt("id"));
                offer.setName(resultSet.getString("name"));
                offer.setEmail(resultSet.getString("email"));
                offer.setText(resultSet.getString("text"));

                return offer;
            }
        });
    }

    public Offer getOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {
            public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
                Offer offer = new Offer();

                offer.setId(resultSet.getInt("id"));
                offer.setName(resultSet.getString("name"));
                offer.setEmail(resultSet.getString("email"));
                offer.setText(resultSet.getString("text"));

                return offer;
            }
        });
    }
}
