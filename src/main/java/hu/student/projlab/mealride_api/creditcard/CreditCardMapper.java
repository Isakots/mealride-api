package hu.student.projlab.mealride_api.creditcard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CreditCardMapper {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CreditCardMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    CreditCard creditCardFormTocreditCard(CreditCardForm form) {
        if(form == null)
            return null;
        else {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(form.getId());
            creditCard.setNumber(form.getNumber());
            creditCard.setOwnername(form.getOwnername());
            creditCard.setExpriationdate(LocalDate.of(form.getExpriationyear(),form.getExpriationmonth(), 0));
            creditCard.setCvc(bCryptPasswordEncoder.encode(form.getCvc()));
            return creditCard;
        }
    }

    CreditCardForm creditCardTocreditCardForm(CreditCard card) {
        if(card == null)
            return null;
        else {
            CreditCardForm form = new CreditCardForm(card);
            return form;
        }
    }

    List<CreditCardForm> creditCardListTocreditCardFormList(List<CreditCard> cardList) {
       return cardList.stream()
                    .filter(Objects::nonNull)
                    .map(this::creditCardTocreditCardForm)
                    .collect(Collectors.toList());
        }
}
