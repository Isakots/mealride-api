package hu.student.projlab.mealride_api.user;


import hu.student.projlab.mealride_api.deliveryaddress.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    private UserService userService;

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public UserController(UserService userService, DeliveryAddressService deliveryAddressService) {
        this.userService = userService;
        this.deliveryAddressService = deliveryAddressService;
    }



}
