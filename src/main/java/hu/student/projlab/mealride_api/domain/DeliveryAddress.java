package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

import static java.util.Objects.hash;

@Getter
@Setter
@Entity
@Table(name="ADDRESS")
@ApiModel(value = "Address Model", description = "Use this model to send data")
public class DeliveryAddress extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "ID of the address", allowEmptyValue = true)
    private Long id;

    @Pattern(regexp="[0-9]{4}", message="ZipCode format is not correct!")
    @NotNull
    @Column(name="ZIP")
    @ApiModelProperty(value = "Zip Code")
    private String zipcode;

    @Size(max=31, message="Length must be less than 32!")
    @NotNull
    @Column(name="CITY")
    @ApiModelProperty(value = "City")
    private String city;

    @Size(max=63, message="Length must be less than 64!")
    @NotNull
    @Column(name="STREET")
    @ApiModelProperty(value = "Street")
    private String street;

    @Size(max=31, message="Length must be less than 32!")
    @NotNull
    @Column(name="STATE")
    @ApiModelProperty(value = "State")
    private String state;

    @Max(value=999, message="House number has to be less than 999!")
    @NotNull
    @Column(name="HOUSE_NUMBER")
    @ApiModelProperty(value = "House Number")
    private Short housenumber;

    @Max(value=100, message="Floor number has to be less than 100")
    @Column(name="FLOOR")
    @ApiModelProperty(value = "Floor")
    private Short floor;

    @Max(value=9999, message="Door number has to be less than 9999")
    @Column(name="DOOR")
    @ApiModelProperty(value = "Door")
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
