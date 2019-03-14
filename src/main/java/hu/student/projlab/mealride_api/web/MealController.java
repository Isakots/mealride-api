package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.service.MealService;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT + EndpointConstants.MENU_ENDPOINT)
class MealController {

    private MealService mealService;

    private UserService userService;

    private RestaurantService restaurantService;

    @Autowired
    public MealController(MealService mealService, UserService userService,
                          RestaurantService restaurantService) {
        this.mealService = mealService;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }



}
