package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.service.validation.password.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    @Email
    private String username;

    @Password
    private String password;

    public UserDTO() { }

    public UserDTO(@Email String username, String password) {
        this.username = username;
        this.password = password;
    }
}
