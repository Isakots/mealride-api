package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
