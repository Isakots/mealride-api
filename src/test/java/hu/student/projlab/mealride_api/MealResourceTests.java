package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.TestUtils;
import hu.student.projlab.mealride_api.web.JwtResponse;
import hu.student.projlab.mealride_api.web.exceptionhandler.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MealResourceTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String endpoint = EndpointConstants.RESTAURANT_ENDPOINT + EndpointConstants.MENU_RESOURCE;

    private Meal mockMeal =
            new Meal("Magyaros pizza (32 cm)", 1450, "pizzaszósz, sonka, szalámi, vöröshagyma, mozzarella");

    private Meal mockMealUpdated =
            new Meal("Magyaros pizza (48 cm)", 2550, "pizzaszósz, sonka, szalámi, vöröshagyma, mozzarella + 1 extra feltét");

    private String sigInURL;

    @Before
    public void init() {
        this.sigInURL = "http://localhost:" + port + TestUtils.contextpath + "/signin";
    }

    @Before
    public void signInUser() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(new UserDTO(TestUtils.username, TestUtils.password), requestHeaders);

        ResponseEntity<JwtResponse> jwtResponse = restTemplate.exchange(
                sigInURL,
                HttpMethod.POST,
                requestEntity,
                JwtResponse.class
        );

        TestUtils.token = jwtResponse.getBody().getAccessToken();
    }

    @Test
    public void getMealsShouldReturnWithMeals() {
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<List<Meal>> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Meal>>() {
                }
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void postAddressWithoutIdShouldBeCreated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Meal> requestEntity = new HttpEntity<>(mockMeal, requestHeaders);
        mockMeal.setId(null);

        ResponseEntity<Meal> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                Meal.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getName(), equalTo("Magyaros pizza (32 cm)"));
        assertThat(response.getBody().getPrice(), equalTo(1450));
        assertThat(response.getBody().getComment(),
                equalTo("pizzaszósz, sonka, szalámi, vöröshagyma, mozzarella"));
    }

    @Test
    public void postAddressWithIdShouldReturnBadRequest() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        mockMeal.setId((long) 9999);

        HttpEntity<Meal> requestEntity = new HttpEntity<>(mockMeal, requestHeaders);

        ResponseEntity<Message> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                Message.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().getMessage(), equalTo("DTO must not contain ID."));
    }

    @Test
    public void putAddressWithoutIdShouldReturnNotFound() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Meal> requestEntity = new HttpEntity<>(mockMeal, requestHeaders);
        mockMeal.setId(null);

        ResponseEntity<Message> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                requestEntity,
                Message.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void putAddressWithIdShouldBeUpdated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Meal> requestEntity = new HttpEntity<>(mockMeal, requestHeaders);
        mockMeal.setId(null);

        ResponseEntity<Meal> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                Meal.class
        );

        mockMealUpdated.setId(POSTresponse.getBody().getId());

        assertThat(POSTresponse.getBody().getId(), notNullValue());

        HttpEntity<Meal> PUTrequestEntity = new HttpEntity<>(mockMealUpdated, requestHeaders);

        ResponseEntity<Meal> PUTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                PUTrequestEntity,
                Meal.class
        );

        assertThat(PUTresponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(PUTresponse.getBody().getName(), equalTo("Magyaros pizza (48 cm)"));
        assertThat(PUTresponse.getBody().getPrice(), equalTo(2550));
        assertThat(PUTresponse.getBody().getComment(),
                equalTo("pizzaszósz, sonka, szalámi, vöröshagyma, mozzarella + 1 extra feltét"));
    }

    @Test
    public void deleteAddressWithValidIdShouldBeDeleted() {

        // Creating Restaurant
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Meal> requestEntity = new HttpEntity<>(mockMeal, requestHeaders);
        mockMeal.setId(null);

        ResponseEntity<Meal> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                Meal.class
        );

        // Deleting Restaurant
        HttpEntity<Void> DELETERequestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<Void> DELETEresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint + "/" + POSTresponse.getBody().getId(),
                HttpMethod.DELETE,
                DELETERequestEntity,
                Void.class
        );

        assertThat(DELETEresponse.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @Test
    public void deleteAddressWithInValidIdShouldReturnNotFound() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Void> DELETERequestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<Void> DELETEresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint + "/" + "9999",
                HttpMethod.DELETE,
                DELETERequestEntity,
                Void.class
        );
        assertThat(DELETEresponse.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }


}
