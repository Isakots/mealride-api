package hu.student.projlab.mealride_api.exception;

public class UserIsNotAuthenticatedException extends RuntimeException {
    private final String message = "User not found.";

    public UserIsNotAuthenticatedException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
