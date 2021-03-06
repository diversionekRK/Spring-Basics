package com.div.spring.web.service;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Div on 2018-04-15.
 */

@Service("offersService")
public class OffersService {
    private OffersDao offersDao;

    @Autowired
    public void setOffersDao(OffersDao offersDao) {
        this.offersDao = offersDao;
    }

    public List<Offer> getCurrentOffers() {
        return offersDao.getOffers();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void create(Offer offer) {
        offersDao.saveOrUpdate(offer);
    }

    public void throwTestException() {
        offersDao.getOffer(Integer.MAX_VALUE);
    }

    public boolean hasOffer(String name) {
        if (name == null)
            return false;

        List<Offer> offers = offersDao.getOffers(name);

        if (offers.size() == 0)
            return false;

        return true;
    }

    public Offer getOffer(String username) {
        if (username == null)
            return null;

        List<Offer> offers = offersDao.getOffers(username);

        if (offers.size() == 0)
            return null;

        return offers.get(0);
    }

    public void saveOrUpdate(Offer offer) {
        offersDao.saveOrUpdate(offer);
    }

    public int delete(int id) {
        return offersDao.delete(id);
    }
}
