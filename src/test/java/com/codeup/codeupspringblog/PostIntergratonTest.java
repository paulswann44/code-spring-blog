package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.AdRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostIntergratonTest {
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = CodeupSpringBlogApplicationTests.class)
    @AutoConfigureMockMvc
    public class AdsIntegrationTests {

        private User testUser;
        private HttpSession httpSession;

        @Autowired
        private MockMvc mvc;
        @Autowired
        UserRepository userDao;

        @Autowired
        AdRepository adsDao;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Before
        public void setUp() Exception{

            if(testUser == null){
                User newUser = new User();
                newUser.setUsername("testUser");
                newUser.setPassword(passwordEncoder.encode("pass"));
                newUser.setEmail("testUser@codeup.com");
                testUser = userDao.save(newUser);
            }
            @Test
            public void contextLoads() {
                // Sanity Test, just to make sure the MVC bean is working
                assertNotNull(mvc);
            }

            @Test
            public void testIfUserSessionIsActive() throws Exception {
                // It makes sure the returned session is not null
                assertNotNull(httpSession);
            }

            @Test
            public void testShowPost() throws Exception {

                Post existingPost = postDao.findAll().get(0);

                // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
                this.mvc.perform(get("/ads/" + existingPost.getId()))
                        .andExpect(status().isOk())
                        // Test the dynamic content of the page
                        .andExpect(content().string(containsString(existingPost.getDescription())));
            }
            @Test
            public void testAdsIndex() throws Exception {
                Post existingPost = postDao.findAll().get(0);

                // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
                this.mvc.perform(get("/ads"))
                        .andExpect(status().isOk())
                        // Test the static content of the page
                        .andExpect(content().string(containsString("Latest ads")))
                        // Test the dynamic content of the page
                        .andExpect(content().string(containsString(existingPost.getTitle())));
            }


        }


    }
}
