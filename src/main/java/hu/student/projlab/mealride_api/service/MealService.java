package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.repository.MealRepository;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
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

    public List<Meal> findAll() {
        // should i ask for it this way or with a native SQL query?
        return findRestaurantWithCurrentUser().getMenu();
        // can it be nullreference ?
    }

    public Meal addMeal(Meal meal) {
        Restaurant restaurant = findRestaurantWithCurrentUser();
        meal.setCreationDate(LocalDateTime.now());
        Meal newMeal = mealRepository.save(meal);

        restaurant.getMenu().add(newMeal);
        restaurantRepository.save(restaurant);
        return newMeal;
    }

    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Restaurant restaurant = findRestaurantWithCurrentUser();

        restaurant.getMenu().remove(meal);
        restaurantRepository.save(restaurant);

        meal.setDeletionDate(LocalDateTime.now());
        mealRepository.save(meal);
    }

    private Restaurant findRestaurantWithCurrentUser() {
        return userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .getRestaurantUser().getRestaurant();
    }
}
