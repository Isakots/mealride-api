package hu.student.projlab.mealride_api.creditcard;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.converter.LocalDateTimeAttributeConverter;
import hu.student.projlab.mealride_api.user.User;
import hu.student.projlab.mealride_api.util.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="CARD")
public class CreditCard extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp="[0-9]{16}", message="Car number format is not correct!")
    @Column(name="CARD_NUMBER")
    private String number;

    @Column(name="OWNER_NAME")
    private String ownername;

    @Future
    @Column(name="EXPIRATION_DATE")
    private Date expriationdate;

    @Column(name="CVC")
    private String cvc; // hashed

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public CreditCard() {
    }

    public CreditCard(@Pattern(regexp = "[0-9]{16}", message = "Car number format is not correct!") String number,
                      String ownername, @Future Date expriationdate, String cvc) {
        this.number = number;
        this.ownername = ownername;
        this.expriationdate = expriationdate;
        this.cvc = cvc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Date getExpriationdate() {
        return expriationdate;
    }

    public void setExpriationdate(Date expriationdate) {
        this.expriationdate = expriationdate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
