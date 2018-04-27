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
import static org.junit.Assert.assertNull;
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

    private User[] users = {
            new User("testuser1", "testuser", "testuser1",
                    "testuser@test.com", true, "ROLE_USER"),
            new User("testuser2", "testuser", "testuser1",
                    "testuser@test.com", true, "ROLE_USER"),
            new User("testuser3", "testuser", "testuser1",
                    "testuser@test.com", true, "ROLE_USER"),
            new User("testuser4", "testuser", "testuser1",
                    "testuser@test.com", false, "ROLE_USER")
    };

    private void saveUsersToDatabase(int from, int to) {
        for(int i = from; i <= to; i++)
            usersDao.create(users[i]);
    }

    private Offer[] offers = {
            new Offer(user1, "This is a test offer..."),
            new Offer(user2, "This is a test offer..."),
            new Offer(user2, "This is a test offer..."),
            new Offer(user3, "This is a test offer..."),
            new Offer(user3, "This is a test offer..."),
            new Offer(user3, "This is a test offer..."),
            new Offer(user4, "This is a test offer...")
    };

    private void saveOffersToDatabase(int from, int to) {
        for(int i = from; i <= to; i++)
            offersDao.saveOrUpdate(offers[i]);
    }

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testCreateRetrieve() {
        saveUsersToDatabase(0, 3);

        saveOffersToDatabase(0, 0);
        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("Should be one offer", 1, offers1.size());
        assertEquals("Retrieved offer should be equal to inserted offer", offers[0], offers1.get(0));

        saveOffersToDatabase(1, 6);

        List<Offer> offers2 = offersDao.getOffers();
        assertEquals("Should be six offers for enabled users", 6, offers2.size());
    }

    @Test
    public void testGetByUsername() {
        saveUsersToDatabase(0, 3);

        saveOffersToDatabase(0, 6);

        List<Offer> offers1 = offersDao.getOffers(users[2].getUsername());
        assertEquals("Should be three offers for this user", 3, offers1.size());

        List<Offer> offers2 = offersDao.getOffers("randomUserXYZ");
        assertEquals("Should be zero offers for unexisting user", 0, offers2.size());

        List<Offer> offers3 = offersDao.getOffers(users[1].getUsername());
        assertEquals("Should be three offers for this user", 2, offers3.size());
    }

    @Test
    public void testUpdate() {
        saveUsersToDatabase(0, 0);
        offersDao.saveOrUpdate(offers[0]);

        offer1.setText("Text set in test method");
        offersDao.saveOrUpdate(offers[0]);
        Offer retrieved = offersDao.getOffer(offers[0].getId());
        assertEquals("Retrieved offer should be updated", offers[0], retrieved);
    }

    @Test
    public void testDelete() {
        saveUsersToDatabase(0, 0);
        offersDao.saveOrUpdate(offers[0]);

        offersDao.delete(offers[0].getId());
        Offer retrieved = offersDao.getOffer(offers[0].getId());
        assertNull("a", retrieved);
    }

    @Test
    public void testGetById() {
        saveUsersToDatabase(0, 3);

        saveOffersToDatabase(0, 6);

        Offer retrieved1 = offersDao.getOffer(offers[0].getId());
        assertEquals("Offers should match", offers[0], retrieved1);

        Offer retrieved2 = offersDao.getOffer(offers[6].getId());
        assertNull("Should not retrieve offer for disabled user", retrieved2);
    }
}
