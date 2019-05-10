package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.service.DeliveryAddressService;
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
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.ADDRESS_RESOURCE)
public class DeliveryAddressResource {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressResource(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping
    @ApiOperation(value = "Find authenticated user's registered addresses",
            response = DeliveryAddress.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Addresses are returned"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Address not found.")}
    )
    public ResponseEntity<List<DeliveryAddress>> getAddresses() {

        List<DeliveryAddress> result = deliveryAddressService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Register a new address to authenticated user",
            response = DeliveryAddress.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address is saved"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated")}
    )
    public ResponseEntity<DeliveryAddress> addAddress(
            @ApiParam(value = "Address what should be registered", required = true)
            @RequestBody @Valid DeliveryAddress address) throws URISyntaxException, InvalidDataException {

        if (address.getId() != null)
            throw new InvalidDataException();

        DeliveryAddress newAddress = deliveryAddressService.addAddress(address);
        return ResponseEntity.created(new URI("/" + newAddress.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Address", newAddress.getId().toString()))
                .body(newAddress);
    }

    @PutMapping
    @ApiOperation(value = "Update address to authenticated user",
            response = DeliveryAddress.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address is updated"),
            @ApiResponse(code = 400, message = "ID is not supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Address not found")}
    )
    public ResponseEntity<DeliveryAddress> updateAddress(
            @ApiParam(value = "Address what should be updated", required = true)
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
    @ApiOperation(value = "Delete address from authenticated user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address is deleted"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Address not found")}
    )
    public ResponseEntity<Void> deleteAddress(
            @ApiParam(value = "ID of address what should be deleted", required = true)
            @PathVariable Long id) throws UserIsNotAuthenticatedException {
        deliveryAddressService.deleteAddress(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert("Address", id.toString()))
                .build();
    }

}
