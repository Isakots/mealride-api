package hu.student.projlab.mealride_api.meal;


import hu.student.projlab.mealride_api.restaurant.RestaurantRepository;
import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private MealRepository mealRepository;

    private RestaurantRepository restaurantRepository;

    private UserService userService;

    @Autowired
    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository, UserService userService) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }


}
