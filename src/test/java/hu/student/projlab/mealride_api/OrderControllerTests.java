//package hu.student.projlab.mealride_api;
//
//import hu.student.projlab.mealride_api.service.dto.UserDTO;
//import hu.student.projlab.mealride_api.util.EndpointConstants;
//import hu.student.projlab.mealride_api.util.TestUtils;
//import hu.student.projlab.mealride_api.web.JwtResponse;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class OrderControllerTests {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private final String endpoint = EndpointConstants.ORDER_ENDPOINT;
//
//    private String sigInURL;
//
//    @Before
//    public void init() {
//        this.sigInURL = "http://localhost:" + port + TestUtils.contextpath + "/signin";
//    }
//
//    @Before
//    public void signInUser() {
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(new UserDTO(TestUtils.username, TestUtils.password), requestHeaders);
//
//        ResponseEntity<JwtResponse> jwtResponse = restTemplate.exchange(
//                sigInURL,
//                HttpMethod.POST,
//                requestEntity,
//                JwtResponse.class
//        );
//
//        TestUtils.token = jwtResponse.getBody().getAccessToken();
//    }
//
//
//}
