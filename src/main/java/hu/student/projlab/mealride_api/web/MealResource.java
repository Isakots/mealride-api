package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.domain.Meal;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.service.MealService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_RESTWORKER') or hasRole('ROLE_RESTADMIN')")
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class MealResource {

    private MealService mealService;

    @Autowired
    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<List<Meal>> getMenu() {
        List<Meal> result = mealService.findAll();
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Meal> addMeal(@RequestBody @Valid Meal meal) throws InvalidDataException {
        if (meal.getId() != null)
            throw new InvalidDataException();
        Meal newMeal = mealService.addMeal(meal);
        return ResponseEntity.ok(newMeal);
    }

    @PutMapping(EndpointConstants.MENU_RESOURCE)
    public ResponseEntity<Meal> updateMeal(@RequestBody @Valid Meal meal) {
        Meal updatedMeal = mealService.updateMeal(meal);
        return ResponseEntity.ok(updatedMeal);
    }

    @DeleteMapping(EndpointConstants.MENU_RESOURCE + "/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok("Meal is deleted successfully");
    }


}
