package com.div.spring.web.test.tests;

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
import static org.junit.Assert.assertFalse;
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
public class UsersDaoTests {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("testuser1", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user2 = new User("testuser2", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user3 = new User("testuser3", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");
    private User user4 = new User("testuser4", "testuser", "testuser1",
            "testuser@test.com", true, "ROLE_USER");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }

    @Test
    public void testCreateRetrieve() {
        usersDao.create(user1);
        List<User> users1 = usersDao.getAllUsers();
        assertEquals("One user should have been created and retrieved", 1, users1.size());
        assertEquals("Inserted user should match retrieved one", user1, users1.get(0));

        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        List<User> users2 = usersDao.getAllUsers();
        assertEquals("Should be four retrieved users", 4, users2.size());
    }

    @Test
    public void testExists() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);

        assertTrue("User should exist", usersDao.exists(user2.getUsername()));
        assertFalse("User should not exist", usersDao.exists("randomTextXYZ"));
    }
}
