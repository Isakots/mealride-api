package hu.student.projlab.mealride_api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.converter.LocalDateAttributeConverter;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "CARD")
public class CreditCard extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @Pattern(regexp = "[0-9]{16}", message = "Car number format is not correct!")
    @Column(name = "CARD_NUMBER")
    private String number;

    @Column(name = "OWNER_NAME")
    private String ownername;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expriationdate;

    @JsonIgnore
    @Column(name = "CVC")
    private String cvc; // hashed

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMERUSER_ID")
    private CustomerUser customer;

    public CreditCard() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id.equals(that.id) &&
                number.equals(that.number) &&
                ownername.equals(that.ownername) &&
                expriationdate.equals(that.expriationdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, ownername, expriationdate);
    }

}
