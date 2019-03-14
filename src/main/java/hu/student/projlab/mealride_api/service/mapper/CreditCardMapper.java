package hu.student.projlab.mealride_api.service.mapper;

import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
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

    public CreditCard creditCardFormTocreditCard(CreditCardDTO cardDTO) {
        if(cardDTO == null)
            return null;
        else {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(cardDTO.getId());
            creditCard.setNumber(cardDTO.getNumber());
            creditCard.setOwnername(cardDTO.getOwnername());
            creditCard.setExpriationdate(LocalDate.of(cardDTO.getExpriationyear(),cardDTO.getExpriationmonth(), 0));
            creditCard.setCvc(bCryptPasswordEncoder.encode(cardDTO.getCvc()));
            return creditCard;
        }
    }

    public CreditCardDTO creditCardTocreditCardForm(CreditCard card) {
        if(card == null)
            return null;
        else {
            CreditCardDTO form = new CreditCardDTO(card);
            return form;
        }
    }

    public List<CreditCardDTO> creditCardListTocreditCardFormList(List<CreditCard> cardList) {
       return cardList.stream()
                    .filter(Objects::nonNull)
                    .map(this::creditCardTocreditCardForm)
                    .collect(Collectors.toList());
        }
}
