package hu.student.projlab.mealride_api.exception;

public class AlreadyAddedToRestaurantException extends Exception {
    private final String message = "User is already added to a restaurant.";

    public AlreadyAddedToRestaurantException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
