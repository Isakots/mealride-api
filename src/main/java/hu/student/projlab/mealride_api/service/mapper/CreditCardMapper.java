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

    public CreditCard creditCardDTOTocreditCard(CreditCardDTO cardDTO) {
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

    public CreditCardDTO creditCardTocreditCardDTO(CreditCard card) {
        if(card == null)
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
