package hu.student.projlab.mealride_api.exception;

public class UserNotFoundException extends Exception {

    private final String message;

    public UserNotFoundException(String username) {
        message = "The specified user with email: \" " + username + "\" is not found.";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
