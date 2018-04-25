package com.div.spring.web.test.tests;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.OffersDAO;
import com.div.spring.web.dao.User;
import com.div.spring.web.dao.UsersDAO;
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
    private OffersDAO offersDAO;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsersDAO usersDAO;

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testOffers() {

        User user = new User("testuser", "testuser", "testuserName", "testuser@test.com", true, "ROLE_USER");
        assertTrue("User creation should return true", usersDAO.create(user));

        Offer offer = new Offer(user, "This is test offer to check if app works");

        assertTrue("Offer creation should return true", offersDAO.create(offer));

        List<Offer> offers = offersDAO.getOffers();

        assertEquals("There should be one offer in a database", 1, offers.size());

        assertEquals("Created offer should be equal to retrieved offer", offer, offers.get(0));
    }
}
