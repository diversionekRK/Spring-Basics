package com.div.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

    public int delete(int id) {
        Query query = session().createQuery("delete from Offer where id = :id");
        query.setLong("id", id);
        return query.executeUpdate();
    }

    public List<Offer> getOffers() {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user", "u");
        criteria.add(Restrictions.eq("u.enabled", true));

        return criteria.list();
    }

    public List<Offer> getOffers(String username) {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user", "u");
        criteria.add(Restrictions.eq("u.enabled", true));
        criteria.add(Restrictions.eq("u.username", username));

        return criteria.list();
    }

    public Offer getOffer(int id) {
        Criteria criteria = session().createCriteria(Offer.class);
        criteria.createAlias("user", "u");
        criteria.add(Restrictions.idEq(id));
        criteria.add(Restrictions.eq("u.enabled", true));

        return (Offer) criteria.uniqueResult();
   }
}
