package hu.student.projlab.mealride_api.restaurant;


import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


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


    @GetMapping
    public ResponseEntity<Object> getRestaurants() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Object> addRestaurant(@RequestHeader String token,
                                                @RequestBody Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> modifyRestaurants(@RequestHeader String token,
                                                    @RequestBody Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteRestaurants(@RequestHeader String token,
                                                    @RequestBody Restaurant restaurant) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }



}
