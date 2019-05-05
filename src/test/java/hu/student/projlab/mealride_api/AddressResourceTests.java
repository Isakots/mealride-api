package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.domain.DeliveryAddress;
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
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressResourceTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String endpoint = "/user/addresses";

    private DeliveryAddress mockAddress =
            new DeliveryAddress("9999", "MockCity", "MockStreet", "MockState", (short) 9, null, null);

    private DeliveryAddress mockAddressUpdated =
            new DeliveryAddress("1111", "MockCityUpdated", "MockStreetUpdated", "MockStateUpdated", (short) 1, null, null);

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
    public void postAddressWithoutIdShouldBeCreated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<DeliveryAddress> requestEntity = new HttpEntity<>(mockAddress, requestHeaders);
        mockAddress.setId(null);

        ResponseEntity<DeliveryAddress> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                DeliveryAddress.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getZipcode(), equalTo("9999"));
        assertThat(response.getBody().getCity(), equalTo("MockCity"));
        assertThat(response.getBody().getStreet(), equalTo("MockStreet"));
        assertThat(response.getBody().getState(), equalTo("MockState"));
        assertThat(response.getBody().getHousenumber(), equalTo((short) 9));
    }

    @Test
    public void postAddressWithIdShouldReturnBadRequest() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        mockAddress.setId((long) 9999);

        HttpEntity<DeliveryAddress> requestEntity = new HttpEntity<>(mockAddress, requestHeaders);

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
        HttpEntity<DeliveryAddress> requestEntity = new HttpEntity<>(mockAddress, requestHeaders);
        mockAddress.setId(null);

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
        HttpEntity<DeliveryAddress> requestEntity = new HttpEntity<>(mockAddress, requestHeaders);
        mockAddress.setId(null);

        ResponseEntity<DeliveryAddress> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                DeliveryAddress.class
        );

        mockAddressUpdated.setId(POSTresponse.getBody().getId());

        assertThat(POSTresponse.getBody().getId(), notNullValue());

        HttpEntity<DeliveryAddress> PUTrequestEntity = new HttpEntity<>(mockAddressUpdated, requestHeaders);

        ResponseEntity<DeliveryAddress> PUTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                PUTrequestEntity,
                DeliveryAddress.class
        );

        assertThat(PUTresponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(PUTresponse.getBody().getZipcode(), equalTo("1111"));
        assertThat(PUTresponse.getBody().getCity(), equalTo("MockCityUpdated"));
        assertThat(PUTresponse.getBody().getStreet(), equalTo("MockStreetUpdated"));
        assertThat(PUTresponse.getBody().getState(), equalTo("MockStateUpdated"));
        assertThat(PUTresponse.getBody().getHousenumber(), equalTo((short) 1));
    }

    @Test
    public void deleteAddressWithValidIdShouldBeDeleted() {

        // Creating Restaurant
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<DeliveryAddress> requestEntity = new HttpEntity<>(mockAddress, requestHeaders);
        mockAddress.setId(null);

        ResponseEntity<DeliveryAddress> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                DeliveryAddress.class
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
