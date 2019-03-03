package hu.student.projlab.mealride_api.creditcard;

import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.CREDITCARD_ENDPOINT)
class CreditCardController {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public ResponseEntity<Object> getCards() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Object> addCard( @RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> modifyCard(@RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCard(@RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }


}
