package hu.student.projlab.mealride_api.restaurant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

   @Query(value="SELECT * FROM Restaurant r WHERE r.id = :id", nativeQuery = true)
   Restaurant getRestaurantById(@Param("id") Long id);

}
