package hu.student.projlab.mealride_api.config.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String RESTWORKER = "ROLE_RESTWORKER";

    public static final String RESTADMIN = "ROLE_RESTADMIN";

    public static final String COURIER = "ROLE_COURIER";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}