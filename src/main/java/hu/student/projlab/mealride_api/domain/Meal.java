package hu.student.projlab.mealride_api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Meal extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "MEAL_NAME")
    @NotNull
    private String name;
    @Column(name = "PRICE")
    @NotNull
    private int price;
    @Column(name = "MEAL_COMMENT")
    @Size(max = 200)
    private String comment;

    public Meal() {
    }

    public Meal(String name, int price, String comment) {
        this.name = name;
        this.price = price;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return price == meal.price &&
                Objects.equals(id, meal.id) &&
                Objects.equals(name, meal.name) &&
                Objects.equals(comment, meal.comment);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                '}';
    }
}
