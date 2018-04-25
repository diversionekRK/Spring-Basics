package com.div.spring.web.test.tests;

import com.div.spring.web.dao.Offer;
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
        "classpath:com/div/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private DataSource dataSource;

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testCreateUser() {
        User user = new User("testuser", "testuser", "testuserName", "testuser@test.com", true, "ROLE_USER");
        assertTrue("User creation should return true", usersDAO.create(user));

        List<User> users = usersDAO.getAllUsers();
        assertEquals("Number of users should be 1", 1, users.size());

        assertTrue("User should exist", usersDAO.exists(user.getUsername()));

        assertEquals("Created user should be equal to retrieved user", user, users.get(0));
    }
}
