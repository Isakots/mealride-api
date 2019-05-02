package hu.student.projlab.mealride_api.exception;

public class InvalidDataException extends Exception {

    private final String message = "DTO must not contain ID.";

    public InvalidDataException() {

    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
