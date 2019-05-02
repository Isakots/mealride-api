package hu.student.projlab.mealride_api;

import com.google.gson.Gson;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealrideApiApplicationTests {

    private UserDTO userWithAllCredentials = new UserDTO("example@mealride.com", "12345");

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() { mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
            .apply(springSecurity())
            .build(); }

    @Test
    public void contextLoads() { }

    @Test
    public void swaggerPage() throws Exception {
        mockMvc.perform(get("/swagger-ui"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void signIn() throws Exception {

        mockMvc.perform(post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.userWithAllCredentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tokenType",equalTo("Bearer")))
                .andExpect(jsonPath(("username"),equalTo("example@mealride.com")));
    }

    // With non-exist user, it cannot be tested this way.
    /*@Test
    @WithMockUser(username = "UserNonExists")
    public void getUserAddressesWithNonExistedUser() throws Exception {

        mockMvc.perform(get("/user/addresses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("message",equalTo("User not found.")));
    }*/

    @Test
    @WithMockUser(username = "test1")
    public void getUserAddresses_IfNoAddressesFound() throws Exception {

        mockMvc.perform(get("/user/addresses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(username = "example@mealride.com")
    public void getUserAddressesWithValidUser() throws Exception {

        DeliveryAddress address = new DeliveryAddress();
        address.setCity("Csesztreg");

        mockMvc.perform(get("/user/addresses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].city", equalTo(address.getCity())));

    }



}
