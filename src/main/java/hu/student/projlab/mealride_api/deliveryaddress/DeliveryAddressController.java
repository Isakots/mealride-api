package hu.student.projlab.mealride_api.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

 }
