package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.domain.ShoppingHours;
import hu.student.projlab.mealride_api.exception.RestaurantNotFoundException;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
import hu.student.projlab.mealride_api.service.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll() {
        List<Restaurant> result = restaurantRepository.findAll();
        return result.stream()
                .filter(restaurant -> restaurant.getDeletionDate() == null)
                .collect(Collectors.toList());
    }

    public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = createRestaurant(restaurantDTO);
        restaurant.setCreationDate(LocalDateTime.now());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantRepository.save(createRestaurant(restaurantDTO));
    }

    public void deleteRestaurant(Long id) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
        restaurant.setDeletionDate(LocalDateTime.now());
        // i cannot delete because there will be a lot of relationship to other entities.
        // TODO plan this scenario
    }

    private Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAvgdeliverytime(restaurantDTO.getAvgdeliverytime());
        restaurant.setMinorderprice(restaurantDTO.getMinorderprice());
        restaurant.setDeliveryprice(restaurantDTO.getDeliveryprice());
        restaurant.setHours(
                new ShoppingHours(formatStringToTime(restaurantDTO.getOpeningtime()),
                        formatStringToTime(restaurantDTO.getClosingtime())));
        return restaurant;
    }

    // TODO change to a not deprecated solution..
    private Time formatStringToTime(String mypattern) {
        String[] split = mypattern.split(":");
        int[] formatted = new int[2];
        formatted[0] = Integer.parseInt(split[0]);
        formatted[1] = Integer.parseInt(split[1]);

        return new Time(formatted[0], formatted[1], 0);
    }

}
