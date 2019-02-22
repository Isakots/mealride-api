package hu.student.projlab.mealride_api.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Query(value="SELECT u.restaurant_id FROM User u WHERE u.user_id = :userid ", nativeQuery =  true)
    Long findRestaurantIdByUserId(@Param("userid") Long userid);

    User getUserById(Long id);
}
