package hu.student.projlab.mealride_api.service.mapper;

import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CreditCardMapper {

    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CreditCardMapper(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public CreditCard creditCardDTOTocreditCard(CreditCardDTO cardDTO) {
        if (cardDTO == null)
            return null;
        else {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(cardDTO.getId());
            creditCard.setNumber(cardDTO.getNumber());
            creditCard.setOwnername(cardDTO.getOwnername());
            creditCard.setExpriationdate(LocalDate.of(cardDTO.getExpriationyear(), cardDTO.getExpriationmonth(), 0));
            creditCard.setCvc(bCryptPasswordEncoder.encode(cardDTO.getCvc()));
            return creditCard;
        }
    }

    public CreditCardDTO creditCardTocreditCardDTO(CreditCard card) {
        if (card == null)
            return null;
        else {
            CreditCardDTO form = new CreditCardDTO(card);
            return form;
        }
    }

    public List<CreditCardDTO> creditCardListTocreditCardDTOList(List<CreditCard> cardList) {
        return cardList.stream()
                .filter(Objects::nonNull)
                .map(this::creditCardTocreditCardDTO)
                .collect(Collectors.toList());
    }

    public List<CreditCard> creditCardDTOListToCreditCardList(List<CreditCardDTO> cardDTOList) {
        return cardDTOList.stream()
                .filter(Objects::nonNull)
                .map(this::creditCardDTOTocreditCard)
                .collect(Collectors.toList());
    }
}
