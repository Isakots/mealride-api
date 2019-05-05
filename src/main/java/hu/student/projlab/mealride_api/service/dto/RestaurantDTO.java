package hu.student.projlab.mealride_api.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestaurantDTO implements Serializable {
    private Long id;
    private String name;
    private String avgdeliverytime;
    private Short minorderprice;
    private Short deliveryprice;
    private String openingtime;
    private String closingtime;
}
