package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.service.dto.RestaurantDTO;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.web.JwtResponse;
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

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String contextpath = "/mealride/api";

    private final String endpoint = "/admin/restaurants";

    private String sigInURL;

    private RestaurantDTO restaurantDTO;

    private RestaurantDTO restaurantDTOUpdated;

    private String token;

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

        this.sigInURL = "http://localhost:" + port + contextpath + "/signin";
    }

    @Before
    public void signInUser() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(new UserDTO("example@mealride.com", "123456"), requestHeaders);

        ResponseEntity<JwtResponse> jwtResponse = restTemplate.exchange(
                sigInURL,
                HttpMethod.POST,
                requestEntity,
                JwtResponse.class
        );

        this.token = jwtResponse.getBody().getAccessToken();
    }

    @Test
    public void assertTokenIsSet() {
        assertNotNull(token);
    }

    @Test
    public void postRestaurantWithoutIdShouldBeCreated() {

        HttpHeaders requestHeaders = setHeaders();

        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, requestHeaders);

        ResponseEntity<RestaurantDTO> response = restTemplate.exchange(
                "http://localhost:" + port + contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));

    }

    private HttpHeaders setHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setBearerAuth(token);
        return requestHeaders;
    }


}
