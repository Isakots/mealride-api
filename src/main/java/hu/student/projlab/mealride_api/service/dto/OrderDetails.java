package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.CartItem;
import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.domain.Restaurant;
import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OrderDetails implements Serializable {
    @NonNull
    private Restaurant restaurant;
    @NonNull
    private DeliveryAddress address;
    @NonNull
    private CreditCard card;
    @Null
    private int price;
    @Max(200)
    private String usercomment;
    @NonNull
    private List<CartItem> meals;
}
