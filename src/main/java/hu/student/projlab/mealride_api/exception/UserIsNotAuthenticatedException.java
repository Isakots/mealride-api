package hu.student.projlab.mealride_api.exception;

import hu.student.projlab.mealride_api.web.exceptionhandler.Message;

public class UserIsNotAuthenticatedException extends Exception {

    private Message message;

    public UserIsNotAuthenticatedException(String message) {
       this.message = new Message(message);
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }
}
