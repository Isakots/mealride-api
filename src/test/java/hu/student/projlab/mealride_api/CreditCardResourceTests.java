package hu.student.projlab.mealride_api;

import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
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
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardResourceTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String endpoint = EndpointConstants.USER_ENDPOINT + EndpointConstants.CREDITCARD_RESOURCE;

    private CreditCardDTO mockCard =
            new CreditCardDTO("1234567890123456", "Csokasi Marcel", 6, 21, "897");

    private CreditCardDTO mockCardUpdated =
            new CreditCardDTO("1234567890123456", "Csokasi Marcell", 7, 22, "887");

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
    public void getCardsShouldReturnWithCards() {
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<List<CreditCardDTO>> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<CreditCardDTO>>() {
                }
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), notNullValue());
    }

    @Test
    public void postCardWithoutIdShouldBeCreated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<CreditCardDTO> requestEntity = new HttpEntity<>(mockCard, requestHeaders);
        mockCard.setId(null);

        ResponseEntity<CreditCardDTO> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                CreditCardDTO.class
        );

        assertThat(response.getBody().getNumber(), equalTo("1234567890123456"));
        assertThat(response.getBody().getOwnername(), equalTo("Csokasi Marcel"));
        assertThat(response.getBody().getExpriationmonth(), equalTo(6));
        assertThat(response.getBody().getExpriationyear(), equalTo(21));
        assertThat(response.getBody().getCvc(), nullValue());
    }

    @Test
    public void postCardWithIdShouldReturnBadRequest() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        mockCard.setId((long) 9999);

        HttpEntity<CreditCardDTO> requestEntity = new HttpEntity<>(mockCard, requestHeaders);

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
    public void putCardWithoutIdShouldReturnNotFound() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<CreditCardDTO> requestEntity = new HttpEntity<>(mockCard, requestHeaders);
        mockCard.setId(null);

        ResponseEntity<Message> response = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                requestEntity,
                Message.class
        );

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void putCardWithIdShouldBeUpdated() {

        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<CreditCardDTO> requestEntity = new HttpEntity<>(mockCard, requestHeaders);
        mockCard.setId(null);

        ResponseEntity<CreditCardDTO> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                CreditCardDTO.class
        );

        mockCardUpdated.setId(POSTresponse.getBody().getId());

        assertThat(POSTresponse.getBody().getId(), notNullValue());

        HttpEntity<CreditCardDTO> PUTrequestEntity = new HttpEntity<>(mockCardUpdated, requestHeaders);

        ResponseEntity<CreditCardDTO> PUTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.PUT,
                PUTrequestEntity,
                CreditCardDTO.class
        );

        assertThat(PUTresponse.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(PUTresponse.getBody().getNumber(), equalTo("1234567890123456"));
        assertThat(PUTresponse.getBody().getOwnername(), equalTo("Csokasi Marcell"));
        assertThat(PUTresponse.getBody().getExpriationmonth(), equalTo(7));
        assertThat(PUTresponse.getBody().getExpriationyear(), equalTo(22));
        assertThat(PUTresponse.getBody().getCvc(), nullValue());
    }

    @Test
    public void deleteCardWithValidIdShouldBeDeleted() {

        // Creating Restaurant
        HttpHeaders requestHeaders = TestUtils.setHeaders();
        HttpEntity<CreditCardDTO> requestEntity = new HttpEntity<>(mockCard, requestHeaders);
        mockCard.setId(null);

        ResponseEntity<CreditCardDTO> POSTresponse = restTemplate.exchange(
                "http://localhost:" + port + TestUtils.contextpath + endpoint,
                HttpMethod.POST,
                requestEntity,
                CreditCardDTO.class
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
    public void deleteCardWithInValidIdShouldReturnNotFound() {

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
