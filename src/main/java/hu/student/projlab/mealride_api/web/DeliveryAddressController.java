package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.repository.UserRepository;
import hu.student.projlab.mealride_api.service.DeliveryAddressService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT + EndpointConstants.ADDRESS_ENDPOINT)
class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    private UserRepository userRepository;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService, UserRepository userRepository) {
        this.deliveryAddressService = deliveryAddressService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAddresses() {


        try {
            Optional<List<DeliveryAddress>> list = deliveryAddressService.findAll();
            return ResponseEntity.ok(list);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addAddress(@RequestBody @Valid DeliveryAddress address,
                                             BindingResult result) {

        /*if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }*/

        try {
            deliveryAddressService.addAddress(address);
            return ResponseEntity.ok(address);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Object> modifyAddress(@RequestBody @Valid DeliveryAddress address,
                                                BindingResult result) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAddress(@RequestBody DeliveryAddress address) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

 }
