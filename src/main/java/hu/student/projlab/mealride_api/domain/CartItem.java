package hu.student.projlab.mealride_api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Meal meal;
    private int amount;

    public CartItem() {
    }

    public CartItem(Meal meal, int amount) {
        this.meal = meal;
        this.amount = amount;
    }

}
