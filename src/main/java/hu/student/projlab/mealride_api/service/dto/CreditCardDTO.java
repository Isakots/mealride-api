package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.CreditCard;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class CreditCardDTO implements Serializable {

    private Long id;
    @Pattern(regexp = "[0-9]{16}", message = "Card number format is not correct!")
    private String number;
    private String ownername;
    private int expriationmonth;
    private int expriationyear;
    private String cvc; // hashed

    public CreditCardDTO() {

    }

    public CreditCardDTO(@Pattern(regexp = "[0-9]{16}", message = "Card number format is not correct!") String number,
                         String ownername, int expriationmonth, int expriationyear, String cvc) {
        this.number = number;
        this.ownername = ownername;
        this.expriationmonth = expriationmonth;
        this.expriationyear = expriationyear;
        this.cvc = cvc;
    }

    public CreditCardDTO(CreditCard card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.ownername = card.getOwnername();
        this.expriationmonth = card.getExpriationdate().getMonthValue();
        this.expriationyear = card.getExpriationdate().getYear();
    }

}
