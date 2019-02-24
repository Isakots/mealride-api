package hu.student.projlab.mealride_api.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequestMapping(value = "/cards")
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
    public ResponseEntity<Object> addCard(@RequestHeader String token,
                                                @RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> modifyCard(@RequestHeader String token,
                                                    @RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCard(@RequestHeader String token,
                                                    @RequestBody CreditCard creditCard) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }


}
