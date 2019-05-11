package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.service.MealService;
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
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class MealResource {

    private MealService mealService;

    @Autowired
    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(EndpointConstants.MENU_RESOURCE)
    @ApiOperation(value = "Find all meals of a Restaurant",
            response = Meal.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Meals are returned"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Meals not found.")}
    )
    public ResponseEntity<List<Meal>> getMenu() {
        List<Meal> result = mealService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.MENU_RESOURCE)
    @ApiOperation(value = "Add a new meal into Restaurant",
            response = Meal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Meal is added to Restaurant"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated")}
    )
    public ResponseEntity<Meal> addMeal(
            @ApiParam(value = "Meal that should be added to Restaurant", required = true)
            @RequestBody @Valid Meal meal) throws InvalidDataException, URISyntaxException {
        if (meal.getId() != null)
            throw new InvalidDataException();
        Meal newMeal = mealService.addMeal(meal);
        return ResponseEntity.created(new URI("/" + newMeal.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(
                        "Address", newMeal.getId().toString()))
                .body(newMeal);
    }

    @PutMapping(EndpointConstants.MENU_RESOURCE)
    @ApiOperation(value = "Update meal in the Restaurant",
            response = Meal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Meal is updated"),
            @ApiResponse(code = 400, message = "ID is not supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Meal not found")}
    )
    public ResponseEntity<Meal> updateMeal(
            @ApiParam(value = "Meal that should be updated in Restaurant", required = true)
            @RequestBody @Valid Meal meal) {
        if (meal.getId() == null)
            return ResponseEntity.notFound()
                    .headers(HeaderUtil.createAlert(
                            "Meal not found", null))
                    .build();
        Meal updatedMeal = mealService.updateMeal(meal);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(
                        "Meal", updatedMeal.getId().toString()))
                .body(updatedMeal);
    }

    @DeleteMapping(EndpointConstants.MENU_RESOURCE + "/{id}")
    @ApiOperation(value = "Remove meal from Restaurant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Meal is removed"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Meal not found")}
    )
    public ResponseEntity<String> deleteMeal(
            @ApiParam(value = "ID of meal that should be removed from Restaurant", required = true)
            @PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok("Meal is deleted successfully");
    }


}
