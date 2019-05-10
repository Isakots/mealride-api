package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.CreditCard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ApiModel(value = "Credit Card Model", description = "Use this model to send data")
public class CreditCardDTO implements Serializable {

    @ApiModelProperty(value = "ID of the card", allowEmptyValue = true)
    private Long id;
    @ApiModelProperty(value = "Card number")
    @Pattern(regexp = "[0-9]{16}", message = "Card number format is not correct!")
    @NotNull
    private String number;
    @ApiModelProperty(value = "Owner name of credit card")
    @NotNull
    private String ownername;
    @ApiModelProperty(value = "Expiration month")
    @NotNull
    private int expriationmonth;
    @ApiModelProperty(value = "Expiration year")
    @NotNull
    private int expriationyear;
    @ApiModelProperty(value = "CVC")
    @NotNull
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
