package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.service.validation.password.Password;

import javax.validation.constraints.*;
import java.io.Serializable;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Short getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(Short housenumber) {
        this.housenumber = housenumber;
    }
}
