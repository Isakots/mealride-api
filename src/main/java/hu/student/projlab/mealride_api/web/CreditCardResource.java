package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.service.CreditCardService;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.CREDITCARD_RESOURCE)
class CreditCardResource {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardResource(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getCards() throws UserIsNotAuthenticatedException {

        List<CreditCardDTO> result = creditCardService.findAll();

        return new ResponseEntity<>(
                result, null, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CreditCardDTO> addCard(
            @RequestBody @Valid CreditCardDTO creditCard) throws Exception {

        if(creditCard.getId() != null)
            throw new Exception();
        // TODO: Create an exceptionhandler class and an Exception type for this.

        CreditCardDTO newCard = creditCardService.addCard(creditCard);
        return ResponseEntity.created(new URI("/" + newCard.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Credit Card", newCard.getId().toString()))
                .body(newCard);
    }

    @PutMapping
    public ResponseEntity<CreditCardDTO> updateCard(
            @RequestBody @Valid CreditCardDTO creditCard) throws UserIsNotAuthenticatedException {

        if(creditCard.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Credit Card not found",null))
                    .build();

        CreditCardDTO result = creditCardService.updateCard(creditCard);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Credit Card", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) throws UserIsNotAuthenticatedException {
        creditCardService.deleteCard(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        "Credit Card", id.toString()))
                .build();
    }

}
