package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class WorkerDTO implements Serializable {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private Set<Role> roles;
}
