package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.repository.MealRepository;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private RestaurantRepository restaurantRepository;

    private MealRepository mealRepository;

    private UserService userService;

    @Autowired
    public MealService(RestaurantRepository restaurantRepository, MealRepository mealRepository, UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
        this.userService = userService;
    }

    public Meal findById(Long id) {

    }

    public List<Meal> findAll() {

    }

    public Meal addMeal(Meal meal) {

    }

    public Meal updateMeal(Meal meal) {

    }

    public void deleteMeal(Long id) {

    }
}
