package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealrideApiApplicationTests {

    // TODO Creating separated test classes for all endpoints and test all HTTP method
    // What tests should cover:
    // 1. Normal scenario -> expected valid data
    // 2. Invalid data -> expected exception
    // 3. User with no permission -> expected resource is denied
    // 4. Adding first resource to database ( for example first meal to restaurant)
    // -> exception must not be occured
    // 5.

    private UserDTO userWithAllCredentials = new UserDTO("example@mealride.com", "12345");

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void swaggerPage() throws Exception {
        mockMvc.perform(get("/swagger-ui"))
                .andExpect(status().is3xxRedirection());
    }
}
