package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal getMealById(Long Id);
}
