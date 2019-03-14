package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.domain.User;
import hu.student.projlab.mealride_api.repository.CreditCardRepository;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
import hu.student.projlab.mealride_api.service.mapper.CreditCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private CreditCardMapper mapper;

    private UserService userService;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, CreditCardMapper mapper, UserService userService) {
        this.creditCardRepository = creditCardRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    List<CreditCardDTO> findAll() {
        if(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()) == null)
            throw new NoSuchElementException("User is not found");

        Optional<List<CreditCard>> cardList =
                creditCardRepository.findAllByUserId(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getId());
        if(cardList.isPresent())
            return mapper.creditCardListTocreditCardFormList(cardList.get());

        return Collections.emptyList();
    }

    void addAddress(CreditCardDTO card) throws NoSuchElementException{
        try {
            User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin());
        }
        catch(NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

    void updateAddress(CreditCardDTO card) {
        try {

        }
        catch(Exception e) {

        }
    }

    void deleteAddress(CreditCardDTO card) {

    }




}
