package hu.student.projlab.mealride_api.domain;

import javax.persistence.*;
import java.io.Serializable;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
