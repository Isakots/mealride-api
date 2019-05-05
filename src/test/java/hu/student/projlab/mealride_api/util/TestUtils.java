package hu.student.projlab.mealride_api.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class TestUtils {

    public static final String contextpath = "/mealride/api";

    public static final String username = "example@mealride.com";

    public static final String password = "123456";

    public static String token;

    public static HttpHeaders setHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setBearerAuth(token);
        return requestHeaders;
    }

    private TestUtils() {
    }


}
