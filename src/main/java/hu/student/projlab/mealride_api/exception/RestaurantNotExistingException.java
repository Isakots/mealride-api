package hu.student.projlab.mealride_api.exception;

public class RestaurantNotExistingException extends Throwable {

    private String message;

    public RestaurantNotExistingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
