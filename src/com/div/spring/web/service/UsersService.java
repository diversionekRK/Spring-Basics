package com.div.spring.web.service;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDAO;
import com.div.spring.web.dao.User;
import com.div.spring.web.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Div on 2018-04-15.
 */

@Service("usersService")
public class UsersService {

    private UsersDAO usersDao;

    @Autowired
    public void setOffersDAO(UsersDAO usersDao) {
        this.usersDao = usersDao;
    }


    public void create(User user) {
        usersDao.create(user);
    }

}
