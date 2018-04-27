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
public class OffersDaoTests {

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsersDao usersDao;

    private User user1 = new User("testuser1", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user2 = new User("testuser2", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user3 = new User("testuser3", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user4 = new User("testuser4", "testuser", "testuser1",
            "testuser@test.com", false, "ROLE_USER");

    private Offer offer1 = new Offer(user1, "This is a test offer...");
    private Offer offer2 = new Offer(user2, "This is a test offer...");
    private Offer offer3 = new Offer(user2, "This is a test offer...");
    private Offer offer4 = new Offer(user3, "This is a test offer...");
    private Offer offer5 = new Offer(user3, "This is a test offer...");
    private Offer offer6 = new Offer(user3, "This is a test offer...");
    private Offer offer7 = new Offer(user4, "This is a test offer...");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testCreateRetrieve() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        offersDao.create(offer1);
        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("Should be one offer", 1, offers1.size());
        assertEquals("Retrieved offer should be equal to inserted offer", offer1, offers1.get(0));

        offersDao.create(offer2);
        offersDao.create(offer3);
        offersDao.create(offer4);
        offersDao.create(offer5);
        offersDao.create(offer6);
        offersDao.create(offer7);

        List<Offer> offers2 = offersDao.getOffers();
        assertEquals("Should be six offers for enabled users", 6, offers2.size());
    }

    @Test
    public void testOffers() {
        usersDao.create(user1);
        offersDao.create(offer1);

        //Getting list of offers test
        List<Offer> offers = offersDao.getOffers();
        assertEquals("There should be one offer in a database", 1, offers.size());
        assertEquals("Created offer should be equal to retrieved offer", offer1, offers.get(0));

        //Updating offer test
        offer1 = offers.get(0);
        offer1.setText("Updated in test offer text");
        assertTrue("Updated offer should return true", offersDao.update(offer1));

        //Querying offer by ID test
        Offer updated = offersDao.getOffer(offer1.getId());
        assertEquals("Updated offer should match retrieved updated offer", offer1, updated);

        //Offer creation test
        Offer offer2 = new Offer(user1, "This is another test offer");
        offersDao.create(offer2);

        //Getting list of offers test
        List<Offer> userOffers = offersDao.getOffers(user1.getUsername());
        assertEquals("Should contain two offers for user", 2, userOffers.size());

        //Getting offers by ID test
        List<Offer> secondList = offersDao.getOffers();
        for (Offer current: secondList) {
            Offer retrieved = offersDao.getOffer(current.getId());
            assertEquals("Offer by ID should match offer from list", current, retrieved);
        }

        //Offer deletion test
        assertTrue("Offer deletion should return true", offersDao.delete(offer1.getId()));

        List<Offer> finalList = offersDao.getOffers();
        assertEquals("Offers list should contain one offer",1, finalList.size());
    }
}
