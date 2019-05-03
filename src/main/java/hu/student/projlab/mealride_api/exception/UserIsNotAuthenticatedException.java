package hu.student.projlab.mealride_api.exception;

public class UserIsNotAuthenticatedException extends Exception {
    private final String message = "User not found.";

    public UserIsNotAuthenticatedException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
