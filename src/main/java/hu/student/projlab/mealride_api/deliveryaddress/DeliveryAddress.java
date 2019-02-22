package hu.student.projlab.mealride_api.deliveryaddress;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.user.User;

import javax.persistence.*;

@Entity
@Table(name="ADDRESS")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="ZIP")
    private Short zipcode;
    @Column(name="CITY")
    private String city;
    @Column(name="STREET")
    private String street;
    @Column(name="STATE")
    private String state;
    @Column(name="HOUSE_NUMBER")
    private Short housenumber;
    @Column(name="FLOOR")
    private Short floor;
    @Column(name="DOOR")
    private Short door;
    @Column(name="CREATED_AT")
    private Long created_at;
    @Column(name="DELETED_AT")
    private Long deleted_at;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public DeliveryAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getZipcode() {
        return zipcode;
    }

    public void setZipcode(Short zipcode) {
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

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Long deleted_at) {
        this.deleted_at = deleted_at;
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
