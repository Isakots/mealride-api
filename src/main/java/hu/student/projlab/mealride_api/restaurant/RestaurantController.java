package hu.student.projlab.mealride_api.restaurant;


import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/restaurant")
class RestaurantController {

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }




}
