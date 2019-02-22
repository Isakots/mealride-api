package hu.student.projlab.mealride_api.meal;

import hu.student.projlab.mealride_api.restaurant.RestaurantService;
import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
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
