package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class MenuController {

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public MenuController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
    @GetMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> getMenu() {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
    @PostMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> addMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
    @PutMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> updateMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
    @DeleteMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Object> deleteMeal(
            @RequestBody @Valid Meal meal) {

        return ResponseEntity.badRequest().body(BAD_REQUEST);
    }


}
