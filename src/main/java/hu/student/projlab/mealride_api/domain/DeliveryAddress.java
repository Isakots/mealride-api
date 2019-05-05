package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

import static java.util.Objects.hash;

@Getter
@Setter
@Entity
@Table(name="ADDRESS")
public class DeliveryAddress extends AbstractEntity{

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


    @ManyToOne
    @JoinColumn(name="CUSTOMERUSER_ID")
    @JsonIgnore
    private CustomerUser customer;

    public DeliveryAddress() { }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryAddress)) return false;
        DeliveryAddress that = (DeliveryAddress) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return hash(id);
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
                ", customerUser=" + customer +
                '}';
    }
}
