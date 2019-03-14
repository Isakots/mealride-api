package hu.student.projlab.mealride_api.service.dto;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private String username;

    @Min(8)
    private String password;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
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
