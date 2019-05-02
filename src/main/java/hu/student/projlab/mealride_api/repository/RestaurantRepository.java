package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "SELECT * FROM Restaurant r WHERE r.id = :id", nativeQuery = true)
    Restaurant getRestaurantById(@Param("id") Long id);

}
