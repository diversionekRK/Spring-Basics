package com.div.spring.web.service;

import com.div.spring.web.dao.User;
import com.div.spring.web.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Div on 2018-04-15.
 */

@Service("usersService")
public class UsersService {

    private UsersDao usersDao;

    @Autowired
    public void setOffersDAO(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public void create(User user) {
        usersDao.create(user);
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

//    @Secured("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
}
