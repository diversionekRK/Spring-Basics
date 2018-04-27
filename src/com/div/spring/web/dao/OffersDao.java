package com.div.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Div on 2018-02-13.
 */

@Repository
@Transactional
@Component
public class OffersDao {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void create(Offer offer) {
        session().save(offer);
    }

    @Transactional
    public int[] create(List<Offer> offers) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());

        return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", params);
    }

    public boolean update(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

        return jdbc.update("update offers set text = :text where id = :id", params) == 1;
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from offers where id = :id", params) == 1;
    }

    public List<Offer> getOffers() {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user", "u");
        criteria.add(Restrictions.eq("u.enabled", true));

        return criteria.list();
    }

    public List<Offer> getOffers(String username) {
        return jdbc.query(
                "select * from offers natural join users where users.enabled = true and users.username = :username",
                new MapSqlParameterSource("username", username)
                , new OfferRowMapper());
    }

    private class OfferRowMapper implements RowMapper<Offer> {
        public Offer mapRow(ResultSet resultSet, int i) throws SQLException {

            User user = new User();
            user.setAuthority(resultSet.getString("authority"));
            user.setEmail(resultSet.getString("email"));
            user.setEnabled(true);
            user.setName(resultSet.getString("name"));
            user.setUsername(resultSet.getString("username"));

            Offer offer = new Offer();
            offer.setId(resultSet.getInt("id"));
            offer.setText(resultSet.getString("text"));
            offer.setUser(user);

            return offer;
        }
    }

    public Offer getOffer(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers natural join users where users.enabled = true and id = :id", params, new OfferRowMapper());
    }
}
