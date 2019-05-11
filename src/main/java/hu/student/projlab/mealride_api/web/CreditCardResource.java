package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.service.CreditCardService;
import hu.student.projlab.mealride_api.service.dto.CreditCardDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.CREDITCARD_RESOURCE)
class CreditCardResource {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardResource(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    @ApiOperation(value = "Find authenticated user's registered credit cards",
            response = CreditCardDTO.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit cards are returned"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Credit Card not found.")}
    )
    public ResponseEntity<List<CreditCardDTO>> getCards() {
        List<CreditCardDTO> result = creditCardService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Register a new credit card to authenticated user",
            response = CreditCardDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit card is saved"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated")}
    )
    public ResponseEntity<CreditCardDTO> addCard(
            @ApiParam(value = "Credit card what should be registered", required = true)
            @RequestBody @Valid CreditCardDTO creditCard) throws URISyntaxException, InvalidDataException {

        if (creditCard.getId() != null)
            throw new InvalidDataException();

        CreditCardDTO newCard = creditCardService.addCard(creditCard);
        return ResponseEntity.created(new URI("/" + newCard.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Credit Card", newCard.getId().toString()))
                .body(newCard);
    }

    @PutMapping
    @ApiOperation(value = "Update a credit card to authenticated user",
            response = CreditCardDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit card is updated"),
            @ApiResponse(code = 400, message = "ID is not supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Credit Card not found")}
    )
    public ResponseEntity<CreditCardDTO> updateCard(
            @ApiParam(value = "Credit card what should be updated", required = true)
            @RequestBody @Valid CreditCardDTO creditCard) {

        if (creditCard.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Credit Card not found", null))
                    .build();

        CreditCardDTO result = creditCardService.updateCard(creditCard);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Credit Card", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a credit card from authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit Card is deleted"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Credit Card not found")}
    )
    public ResponseEntity<Void> deleteCard(
            @ApiParam(value = "ID of credit card what should be deleted", required = true)
            @PathVariable Long id) {
        creditCardService.deleteCard(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        "Credit Card", id.toString()))
                .build();
    }

}
