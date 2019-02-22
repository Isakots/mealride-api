package hu.student.projlab.mealride_api.creditcard;


import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.creditCardRepository = creditCardRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }


}
