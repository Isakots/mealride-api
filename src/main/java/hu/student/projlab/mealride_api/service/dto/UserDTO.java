package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.service.validation.password.Password;

import javax.validation.constraints.Email;
import java.io.Serializable;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
