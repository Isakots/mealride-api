package hu.student.projlab.mealride_api.meal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal getMealById(Long Id);
}
