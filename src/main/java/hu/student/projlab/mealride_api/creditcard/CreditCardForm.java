package hu.student.projlab.mealride_api.creditcard;

public class CreditCardForm {

    private Long number;
    private String ownername;
    private String expriationmonth;
    private String expriationyear;
    private String cvc; // hashed

    public CreditCardForm() {
    }

    public CreditCardForm(Long number, String ownername, String expriationmonth, String expriationyear, String cvc) {
        this.number = number;
        this.ownername = ownername;
        this.expriationmonth = expriationmonth;
        this.expriationyear = expriationyear;
        this.cvc = cvc;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getExpriationmonth() {
        return expriationmonth;
    }

    public void setExpriationmonth(String expriationmonth) {
        this.expriationmonth = expriationmonth;
    }

    public String getExpriationyear() {
        return expriationyear;
    }

    public void setExpriationyear(String expriationyear) {
        this.expriationyear = expriationyear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
