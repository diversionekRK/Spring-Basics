package com.div.spring.web.service;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Div on 2018-04-15.
 */

@Service("offersService")
public class OffersService {
    private OffersDAO offersDAO;

    @Autowired
    public void setOffersDAO(OffersDAO offersDAO) {
        this.offersDAO = offersDAO;
    }

    public List<Offer> getCurrentOffers() {
        return offersDAO.getOffers();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void create(Offer offer) {
        offersDAO.create(offer);
    }

    public void throwTestException() {
        offersDAO.getOffer(Integer.MAX_VALUE);
    }
}
