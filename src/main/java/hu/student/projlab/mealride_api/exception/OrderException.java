package hu.student.projlab.mealride_api.exception;

public class OrderException extends Exception {

    private String message;

    public OrderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
