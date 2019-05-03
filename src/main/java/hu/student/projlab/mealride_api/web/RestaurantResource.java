package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.RestaurantNotFoundException;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = EndpointConstants.ADMIN_ENDPOINT + EndpointConstants.RESTAURANT_RESOURCE)
class RestaurantResource {

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public RestaurantResource(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> result = restaurantService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(
            @RequestBody @Valid Restaurant restaurant) throws InvalidDataException, URISyntaxException {

        if (restaurant.getId() != null)
            throw new InvalidDataException();

        Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.created(new URI("/" + newRestaurant.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Restaurant", newRestaurant.getId().toString()))
                .body(newRestaurant);
    }

    @PutMapping
    public ResponseEntity<Object> updateRestaurant(
            @RequestBody @Valid Restaurant restaurant) {

        if (restaurant.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Restaurant not found", null))
                    .build();

        Restaurant result = restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Restaurant", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRestaurant(@RequestBody @Valid Long id) throws RestaurantNotFoundException {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        "Restaurant", id.toString()))
                .build();
    }


}
