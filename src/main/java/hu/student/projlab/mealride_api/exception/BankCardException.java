package hu.student.projlab.mealride_api.exception;

public class BankCardException extends Exception {

    private String message;

    public BankCardException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
