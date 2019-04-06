package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class RestaurantController {

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }


    @GetMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<Object> getWorkers() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PostMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<Object> addWorker(
            @RequestBody @Valid UserDTO userDTO) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<Object> updateWorker(
            @RequestBody @Valid UserDTO userDTO) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<Object> deleteWorker(
            @RequestBody @Valid UserDTO userDTO) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }




    @GetMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> getMenu() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PostMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> addMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PutMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> updateMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @DeleteMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> deleteMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }



}
