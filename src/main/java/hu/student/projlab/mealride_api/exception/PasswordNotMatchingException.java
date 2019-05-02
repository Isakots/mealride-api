package hu.student.projlab.mealride_api.exception;

public class PasswordNotMatchingException extends Exception {

    private String message;

    public PasswordNotMatchingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
