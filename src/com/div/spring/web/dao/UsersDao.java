package com.div.spring.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Component
@Transactional
public class UsersDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        session().save(user);
    }

    public boolean exists(String username) {
        Criteria criteria = session().createCriteria(User.class);
        criteria.add(Restrictions.idEq(username));
        User user = (User) criteria.uniqueResult();

        return user != null;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return session().createQuery("from User").list();
    }
}
