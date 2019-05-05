package hu.student.projlab.mealride_api.web.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

}
