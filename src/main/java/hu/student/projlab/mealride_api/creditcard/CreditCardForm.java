package hu.student.projlab.mealride_api.creditcard;

import javax.validation.constraints.Pattern;

public class CreditCardForm {

    private Long id;
    @Pattern(regexp="[0-9]{16}", message="Car number format is not correct!")
    private String number;
    private String ownername;
    private int expriationmonth;
    private int expriationyear;
    private String cvc; // hashed

    public CreditCardForm() {

    }

    public CreditCardForm(Long id, @Pattern(regexp = "[0-9]{16}", message = "Car number format is not correct!") String number,
                          String ownername, int expriationmonth, int expriationyear, String cvc) {
        this.id = id;
        this.number = number;
        this.ownername = ownername;
        this.expriationmonth = expriationmonth;
        this.expriationyear = expriationyear;
        this.cvc = cvc;
    }

    CreditCardForm(CreditCard card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.ownername = card.getOwnername();
        this.expriationmonth = card.getExpriationdate().getMonthValue();
        this.expriationyear = card.getExpriationdate().getYear();
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

    public int getExpriationmonth() {
        return expriationmonth;
    }

    public void setExpriationmonth(int expriationmonth) {
        this.expriationmonth = expriationmonth;
    }

    public int getExpriationyear() {
        return expriationyear;
    }

    public void setExpriationyear(int expriationyear) {
        this.expriationyear = expriationyear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
