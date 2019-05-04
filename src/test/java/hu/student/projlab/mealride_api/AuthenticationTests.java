package hu.student.projlab.mealride_api;

import com.google.gson.Gson;
import hu.student.projlab.mealride_api.service.dto.SignUpForm;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTests {

    private final UserDTO userWithAllCredentials = new UserDTO("example@mealride.com", "123456");
    private final SignUpForm formNotRegistered = new SignUpForm("example@mealride.comm", "123456",
            "Marcell", "Csokasi", "301234567", "1356", "Szeged", "Béka u.",
            "Csongrád megye", (short) 20, null, null);
    private final SignUpForm formAlreadyRegistered = new SignUpForm("example@mealride.com", "123456",
            "Marcell", "Csokasi", "301234567", "1356", "Szeged", "Béka u.",
            "Csongrád megye", (short) 20, null, null);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void signInWithValidCredentialsShouldAuthenticate() throws Exception {
        mockMvc.perform(post("/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.userWithAllCredentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tokenType", equalTo("Bearer")))
                .andExpect(jsonPath(("username"), equalTo("example@mealride.com")));
    }

    @Test
    public void signUpWithValidCredentialsShouldAuthenticate() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.formNotRegistered)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("message", equalTo("User registered successfully!")));
    }

    @Test
    public void signUpWithExistingCredentialsShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(this.formAlreadyRegistered)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", equalTo("Fail -> Username is already taken!")));
    }

}
