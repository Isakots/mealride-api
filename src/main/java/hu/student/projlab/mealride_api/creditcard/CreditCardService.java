package hu.student.projlab.mealride_api.creditcard;


import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.user.User;
import hu.student.projlab.mealride_api.user.UserService;
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

    List<CreditCardForm> findAll() {
        if(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()) == null)
            throw new NoSuchElementException("User is not found");

        Optional<List<CreditCard>> cardList =
                creditCardRepository.findAllByUserId(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getId());
        if(cardList.isPresent())
            return mapper.creditCardListTocreditCardFormList(cardList.get());

        return Collections.emptyList();
    }

    void addAddress(CreditCardForm card) throws NoSuchElementException{
        try {
            User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin());
        }
        catch(NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

    void updateAddress(CreditCardForm card) {
        try {

        }
        catch(Exception e) {

        }
    }

    void deleteAddress(CreditCardForm card) {

    }




}
