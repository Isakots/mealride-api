package hu.student.projlab.mealride_api.deliveryaddress;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.user.User;
import hu.student.projlab.mealride_api.util.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="ADDRESS")
public class DeliveryAddress extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp="[0-9]{4}", message="ZipCode format is not correct!")
    @Column(name="ZIP")
    private String zipcode;

    @Size(max=31, message="Length must be less than 32!")
    @Column(name="CITY")
    private String city;

    @Size(max=63, message="Length must be less than 64!")
    @Column(name="STREET")
    private String street;

    @Size(max=31, message="Length must be less than 32!")
    @Column(name="STATE")
    private String state;

    @Max(value=999, message="House number has to be less than 999!")
    @Column(name="HOUSE_NUMBER")
    private Short housenumber;

    @Max(value=100, message="Floor number has to be less than 100")
    @Column(name="FLOOR")
    private Short floor;

    @Max(value=9999, message="Door number has to be less than 9999")
    @Column(name="DOOR")
    private Short door;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public DeliveryAddress() {

    }

    public DeliveryAddress(@Pattern(regexp = "[0-9]{4}") String zipcode, @Size(max = 31) String city, @Size(max = 63) String street,
                           @Size(max = 31) String state, @Max(999) Short housenumber, @Max(100) Short floor, @Max(9999) Short door) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.state = state;
        this.housenumber = housenumber;
        this.floor = floor;
        this.door = door;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public Short getDoor() {
        return door;
    }

    public void setDoor(Short door) {
        this.door = door;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "id=" + id +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", housenumber=" + housenumber +
                ", floor=" + floor +
                ", door=" + door +
                ", user=" + user +
                '}';
    }
}
