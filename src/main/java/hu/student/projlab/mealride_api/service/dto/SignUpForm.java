package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.service.validation.password.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;


@Getter
@Setter
public class SignUpForm implements Serializable {
    @Email
    private String username;
    @Password
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;

    @Pattern(regexp = "[0-9]{4}", message = "ZipCode format is not correct!")
    @NotNull
    private String zipcode;

    @Size(max = 31, message = "Length must be less than 32!")
    @NotNull
    private String city;

    @Size(max = 63, message = "Length must be less than 64!")
    @NotNull
    private String street;

    @Size(max = 31, message = "Length must be less than 32!")
    @NotNull
    private String state;

    @Max(value = 999, message = "House number has to be less than 999!")
    @NotNull
    private Short housenumber;

    @Max(value = 100, message = "Floor number has to be less than 100")
    private Short floor;

    @Max(value = 9999, message = "Door number has to be less than 9999")
    private Short door;

    public SignUpForm(@Email String username, String password, @NotNull String firstname, @NotNull String lastname,
                      @NotNull String phone, @Pattern(regexp = "[0-9]{4}", message = "ZipCode format is not correct!") @NotNull String zipcode,
                      @Size(max = 31, message = "Length must be less than 32!") @NotNull String city, @Size(max = 63,
            message = "Length must be less than 64!") @NotNull String street, @Size(max = 31, message = "Length must be less than 32!") @NotNull String state,
                      @Max(value = 999, message = "House number has to be less than 999!") @NotNull Short housenumber, @Max(value = 100,
            message = "Floor number has to be less than 100") Short floor, @Max(value = 9999, message = "Door number has to be less than 9999") Short door) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.state = state;
        this.housenumber = housenumber;
        this.floor = floor;
        this.door = door;
    }
}
