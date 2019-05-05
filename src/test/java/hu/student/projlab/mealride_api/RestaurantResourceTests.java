package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.service.dto.RestaurantDTO;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantResourceTests {

    // TODO Refactor code duplications. --> Create a Generic method for performing POST method.

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String endpoint = "/admin/restaurants";

    private String sigInURL;

    private RestaurantDTO restaurantDTO;

    private RestaurantDTO restaurantDTOUpdated;

    @Before
    public void initObjects() {
        restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName("TestName");
        restaurantDTO.setAvgdeliverytime("TestDeliveryTime");
        restaurantDTO.setMinorderprice((short) 9999);
        restaurantDTO.setDeliveryprice((short) 9999);
        restaurantDTO.setOpeningtime("2:22");
        restaurantDTO.setClosingtime("22:22");

        restaurantDTOUpdated = new RestaurantDTO();
        restaurantDTOUpdated.setName("TestNameUpdated");
        restaurantDTOUpdated.setAvgdeliverytime("TestDeliveryTimeUpdated");
        restaurantDTOUpdated.setMinorderprice((short) 8888);
        restaurantDTOUpdated.setDeliveryprice((short) 8888);
        restaurantDTOUpdated.setOpeningtime("3:33");
        restaurantDTOUpdated.setClosingtime("21:21");

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
    public void assertTokenIsSet() {
        assertNotNull(TestUtils.token);
    }

    @Test
    public void postRestaurantWithoutIdShouldBeCreated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);
        restaurantDTO.setId(null);

        ResponseEntity<RestaurantDTO> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getName(), equalTo("TestName"));
        assertThat(response.getBody().getAvgdeliverytime(), equalTo("TestDeliveryTime"));
        assertThat(response.getBody().getMinorderprice(), equalTo((short) 9999));
        assertThat(response.getBody().getDeliveryprice(), equalTo((short) 9999));
        //assertThat(response.getBody().getOpeningtime(), equalTo("2:22"));
        // assertThat(response.getBody().getClosingtime(), equalTo("22:22"));
    }

    @Test
    public void postRestaurantWithIdShouldReturnBadRequest() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        restaurantDTO.setId((long) 9999);

        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);

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
    public void putRestaurantWithoutIdShouldReturnNotFound() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);
        restaurantDTO.setId(null);

        ResponseEntity<Message> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                requestEntity,
                Message.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void putRestaurantWithIdShouldBeUpdated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);
        restaurantDTO.setId(null);

        ResponseEntity<RestaurantDTO> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
        );

        restaurantDTOUpdated.setId(POSTresponse.getBody().getId());
        HttpEntity<RestaurantDTO> PUTrequestEntity = new HttpEntity<>(restaurantDTOUpdated, requestHeaders);

        ResponseEntity<RestaurantDTO> PUTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                PUTrequestEntity,
                RestaurantDTO.class
        );

        assertThat(PUTresponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(PUTresponse.getBody().getName(), equalTo("TestNameUpdated"));
        assertThat(PUTresponse.getBody().getAvgdeliverytime(), equalTo("TestDeliveryTimeUpdated"));
        assertThat(PUTresponse.getBody().getMinorderprice(), equalTo((short) 8888));
        assertThat(PUTresponse.getBody().getDeliveryprice(), equalTo((short) 8888));
        //assertThat(PUTresponse.getBody().getOpeningtime(), equalTo("3:33"));
        //assertThat(PUTresponse.getBody().getClosingtime(), equalTo("21:21"));
    }

    @Test
    public void deleteRestaurantWithValidIdShouldBeDeleted() {

        // Creating Restaurant
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);
        restaurantDTO.setId(null);

        ResponseEntity<RestaurantDTO> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
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
    public void deleteRestaurantWithInValidIdShouldReturnNotFound() {

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
