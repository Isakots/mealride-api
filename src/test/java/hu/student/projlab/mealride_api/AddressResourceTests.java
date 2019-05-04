package hu.student.projlab.mealride_api;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
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
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressResourceTests {

    private final String urlTemplate = "/user/addresses";

    private final String username = "example@mealride.com";

    private DeliveryAddress mockAddress =
            new DeliveryAddress("9999", "MockCity", "MockStreet", "MockState", (short) 9, null, null);

    private DeliveryAddress mockAddressUpdated =
            new DeliveryAddress("1111", "MockCityUpdated", "MockStreetUpdated", "MockStateUpdated", (short) 1, null, null);

    private Long storedID;


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                //.apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = username)
    public void postUserAddressWithoutIdShouldBeCreated() throws Exception {

        postData();

        mockMvc.perform(get(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].id", notNullValue()))
                .andExpect(jsonPath("$[2].zipcode", equalTo("9999")))
                .andExpect(jsonPath("$[2].city", equalTo("MockCity")))
                .andExpect(jsonPath("$[2].street", equalTo("MockStreet")))
                .andExpect(jsonPath("$[2].state", equalTo("MockState")))
                .andExpect(jsonPath("$[2].housenumber", equalTo(9)));

    }

    @Test
    @WithMockUser(username = username)
    public void postUserAddressWithIdShouldThrowException() throws Exception {

        mockAddress.setId((long) 9999);

        mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockAddress)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", equalTo("DTO must not contain ID.")));

    }
/*
    @Test
    @WithMockUser(username = username)
    public void putUserAddressWithoutIdShouldReturnNotFound() throws Exception {

        mockMvc.perform(put(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockAddressUpdated)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = username)
    public void putUserAddressWithIdShouldBeUpdated() throws Exception {

        postData();

        mockMvc.perform(put(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockAddressUpdated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(storedID)))
                .andExpect(jsonPath("zipcode", equalTo("1111")))
                .andExpect(jsonPath("city", equalTo("MockCityUpdated")))
                .andExpect(jsonPath("street", equalTo("MockStreetUpdated")))
                .andExpect(jsonPath("state", equalTo("MockStateUpdated")))
                .andExpect(jsonPath("housenumber", equalTo((short) 1)));
    }
*/

    @Test
    @WithMockUser(username = username)
    public void deleteUserAddressWithValidIdShouldBeDeleted() throws Exception {

        postData();

        mockMvc.perform(delete(urlTemplate + "/" + storedID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


   /* @Test
    @WithMockUser(username = username)
    public void deleteUserAddressWithInvalidIdShouldThrowException() throws Exception {

        storedID = (long) 9999;

        mockMvc.perform(delete(urlTemplate + "/" + storedID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //.andExpect(jsonPath("message", equalTo("Elements not found.")));
    }*/


    private void postData() throws Exception {
        mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockAddress)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", notNullValue()))
                .andExpect(jsonPath("zipcode", equalTo("9999")))
                .andExpect(jsonPath("city", equalTo("MockCity")))
                .andExpect(jsonPath("street", equalTo("MockStreet")))
                .andExpect(jsonPath("state", equalTo("MockState")))
                .andExpect(jsonPath("housenumber", equalTo(9)))
                .andDo(mvcResult -> {
                    String jsondata = mvcResult.getResponse().getContentAsString();
                    storedID = Long.parseLong(JsonPath.parse(jsondata).read("id").toString());
                });
    }

}
