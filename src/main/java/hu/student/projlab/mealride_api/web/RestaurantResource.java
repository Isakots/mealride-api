package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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
    public ResponseEntity<Object> getRestaurants() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Object> addRestaurant(
            @RequestBody @Valid Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateRestaurant(
            @RequestBody @Valid Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRestaurant(
            @RequestBody @Valid Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }



}
