package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.service.DeliveryAddressService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.ADDRESS_RESOURCE)
class DeliveryAddressResource {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressResource(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAddress>> getAddresses() {

        List<DeliveryAddress> result = deliveryAddressService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryAddress> addAddress
            (@RequestBody @Valid DeliveryAddress address) throws URISyntaxException {

        if (address.getId() != null)
            throw new NoSuchElementException();
        // TODO: Create an exceptionhandler class and an Exception type for this.

        DeliveryAddress newAddress = deliveryAddressService.addAddress(address);
        return ResponseEntity.created(new URI("/" + newAddress.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Address", newAddress.getId().toString()))
                .body(newAddress);
    }

    @PutMapping
    public ResponseEntity<DeliveryAddress> updateAddress(
            @RequestBody @Valid DeliveryAddress address) {
        if (address.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Address not found", null))
                    .build();

        DeliveryAddress result = deliveryAddressService.updateAddress(address);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Address", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws UserIsNotAuthenticatedException {
        deliveryAddressService.deleteAddress(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert("Address", id.toString()))
                .build();
    }

    @ExceptionHandler(UserIsNotAuthenticatedException.class)
    protected ResponseEntity<?> handleUserNotAuthenticated(UserIsNotAuthenticatedException e) {
        return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
    }

}
