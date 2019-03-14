package hu.student.projlab.mealride_api.service;


import hu.student.projlab.mealride_api.repository.MealRepository;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
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
