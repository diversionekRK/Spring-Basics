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

        offersDao.saveOrUpdate(offer1);
        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("Should be one offer", 1, offers1.size());
        assertEquals("Retrieved offer should be equal to inserted offer", offer1, offers1.get(0));

        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        List<Offer> offers2 = offersDao.getOffers();
        assertEquals("Should be six offers for enabled users", 6, offers2.size());
    }

    @Test
    public void testGetByUsername() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
        assertEquals("Should be three offers for this user", 3, offers1.size());

        List<Offer> offers2 = offersDao.getOffers("randomUserXYZ");
        assertEquals("Should be zero offers for unexisting user", 0, offers2.size());

        List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
        assertEquals("Should be three offers for this user", 2, offers3.size());
    }

    @Test
    public void testUpdate() {
        usersDao.create(user1);
        offersDao.saveOrUpdate(offer1);

        offer1.setText("Text set in test method");
        offersDao.saveOrUpdate(offer1);
        Offer retrieved = offersDao.getOffer(offer1.getId());
        assertEquals("Retrieved offer should be updated", offer1, retrieved);
    }

    @Test
    public void testDelete() {
        usersDao.create(user1);
        offersDao.saveOrUpdate(offer1);

        offersDao.delete(offer1.getId());
        Offer retrieved = offersDao.getOffer(offer1.getId());
        assertNull("a", retrieved);
    }

    @Test
    public void testGetById() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        Offer retrieved1 = offersDao.getOffer(offer1.getId());
        assertEquals("Offers should match", offer1, retrieved1);

        Offer retrieved2 = offersDao.getOffer(offer7.getId());
        assertNull("Should not retrieve offer for disabled user", retrieved2);
    }
}
