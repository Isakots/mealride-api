package hu.student.projlab.mealride_api.exception;

public class RestaurantNotFoundException extends Exception {

    private final String message = "Restaurant not found.";

    public RestaurantNotFoundException() {}

    @Override
    public String getMessage() {
        return message;
    }
}
