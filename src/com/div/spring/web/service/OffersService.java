package com.div.spring.web.service;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDAO;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Offer> getCurrent() {
        return offersDAO.getOffers();
    }

    public void create(Offer offer) {
        offersDAO.create(offer);
    }

    public void throwTestException() {
        offersDAO.getOffer(11111);
    }
}
