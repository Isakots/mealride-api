package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.service.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDTO implements Serializable {

    @Email
    private String username;

    @Password
    private String password;

    public UserDTO() {
    }

    public UserDTO(@Email @NotBlank String username, @Min(8) @NotBlank String password) {
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
