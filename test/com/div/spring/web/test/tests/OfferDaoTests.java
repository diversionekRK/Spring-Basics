package com.div.spring.web.test.tests;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDao;
import com.div.spring.web.dao.User;
import com.div.spring.web.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Div on 2018-04-25.
 */

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/div/spring/web/config/dao-context.xml",
        "classpath:com/div/spring/web/config/security-config.xml",
        "classpath:com/div/spring/web/test/config/datasource.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsersDao usersDao;

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testOffers() {

        //User creation test
        User user = new User("testuser", "testuser", "testuserName", "testuser@test.com", true, "ROLE_USER");
        assertTrue("User creation should return true", usersDao.create(user));

        //Offer creation test
        Offer offer = new Offer(user, "This is test offer to check if app works");
        assertTrue("Offer creation should return true", offersDao.create(offer));

        //Getting list of offers test
        List<Offer> offers = offersDao.getOffers();
        assertEquals("There should be one offer in a database", 1, offers.size());
        assertEquals("Created offer should be equal to retrieved offer", offer, offers.get(0));

        //Updating offer test
        offer = offers.get(0);
        offer.setText("Updated in test offer text");
        assertTrue("Updated offer should return true", offersDao.update(offer));

        //Querying offer by ID test
        Offer updated = offersDao.getOffer(offer.getId());
        assertEquals("Updated offer should match retrieved updated offer", offer, updated);

        //Offer creation test
        Offer offer2 = new Offer(user, "This is another test offer");
        assertTrue("Offer creation should return true", offersDao.create(offer2));

        //Getting list of offers test
        List<Offer> userOffers = offersDao.getOffers(user.getUsername());
        assertEquals("Should contain two offers for user", 2, userOffers.size());

        //Getting offers by ID test
        List<Offer> secondList = offersDao.getOffers();
        for (Offer current: secondList) {
            Offer retrieved = offersDao.getOffer(current.getId());
            assertEquals("Offer by ID should match offer from list", current, retrieved);
        }

        //Offer deletion test
        assertTrue("Offer deletion should return true", offersDao.delete(offer.getId()));

        List<Offer> finalList = offersDao.getOffers();
        assertEquals("Offers list should contain one offer",1, finalList.size());
    }
}
