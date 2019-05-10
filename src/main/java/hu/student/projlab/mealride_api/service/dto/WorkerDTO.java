package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "Worker Model", description = "Use this model to send data")
public class WorkerDTO implements Serializable {
    @ApiModelProperty(value = "ID of worker")
    private Long id;
    @ApiModelProperty(value = "Worker username")
    private String username;
    @ApiModelProperty(value = "Worker's firstname", allowEmptyValue = true)
    private String firstname;
    @ApiModelProperty(value = "Worker's lastname", allowEmptyValue = true)
    private String lastname;
    @ApiModelProperty(value = "Worker's phone number", allowEmptyValue = true)
    private String phone;
    @ApiModelProperty(value = "Worker roles", allowEmptyValue = true)
    private Set<Role> roles;
}
