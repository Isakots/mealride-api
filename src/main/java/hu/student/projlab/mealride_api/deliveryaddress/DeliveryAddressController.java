package hu.student.projlab.mealride_api.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = "/addresses")
class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping
    public ResponseEntity<Object> getAddresses() {

        try {
            List<DeliveryAddress> list = deliveryAddressService.findAll();
            return ResponseEntity.ok(list);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addAddress(@RequestHeader(required = false) String token,
                                             @RequestBody @Valid DeliveryAddress address,
                                             BindingResult result) {

        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            deliveryAddressService.addAddress(address);
            return ResponseEntity.ok(address);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Object> modifyAddress(@RequestHeader(required = false) String token,
                                             @RequestBody DeliveryAddress address) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAddress(@RequestHeader(required = false) String token,
                                             @RequestBody DeliveryAddress address) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

 }
