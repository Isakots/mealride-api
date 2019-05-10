package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.RestaurantNotFoundException;
import hu.student.projlab.mealride_api.service.RestaurantService;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.service.dto.RestaurantDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import hu.student.projlab.mealride_api.util.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @ApiOperation(value = "Find all restaurants",
            response = Restaurant.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Restaurants are returned"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Resturant not found.")}
    )
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> result = restaurantService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Register a new restaurant into the system",
            response = Restaurant.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Restaurant is saved"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated")}
    )
    public ResponseEntity<Restaurant> addRestaurant(
            @ApiParam(value = "Restaurant what should be registered", required = true)
            @RequestBody @Valid RestaurantDTO restaurantDTO) throws URISyntaxException, InvalidDataException {

        if (restaurantDTO.getId() != null)
            throw new InvalidDataException();

        Restaurant newRestaurant = restaurantService.addRestaurant(restaurantDTO);
        return ResponseEntity.created(new URI("/" + newRestaurant.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Restaurant", newRestaurant.getId().toString()))
                .body(newRestaurant);
    }

    @PutMapping
    @ApiOperation(value = "Update restaurant",
            response = Restaurant.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Restaurant is updated"),
            @ApiResponse(code = 400, message = "ID is not supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Restaurant not found")}
    )
    public ResponseEntity<Restaurant> updateRestaurant(
            @ApiParam(value = "Restaurant what should be updated", required = true)
            @RequestBody @Valid RestaurantDTO restaurantDTO) {

        if (restaurantDTO.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Restaurant not found", null))
                    .build();

        Restaurant result = restaurantService.updateRestaurant(restaurantDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Restaurant", result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete restaurant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Restaurant is deleted"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Restaurant not found")}
    )
    public ResponseEntity<Void> deleteRestaurant(
            @ApiParam(value = "ID of restaurant what should be deleted", required = true)
            @PathVariable @Valid Long id) throws RestaurantNotFoundException {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        "Restaurant", id.toString()))
                .build();
    }


}
