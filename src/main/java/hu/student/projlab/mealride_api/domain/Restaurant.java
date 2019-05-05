package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.user.RestaurantUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Restaurant extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "RESTAURANT_NAME")
    private String name;
    @Column(name = "AVERAGE_DELIVERY_TIME")
    private String avgdeliverytime;
    @Column(name = "MINIMUM_ORDER_PRICE")
    private Short minorderprice;
    @Column(name = "DELIVERY_PRICE")
    private Short deliveryprice;

    @Embedded
    private ShoppingHours hours;

    @OneToMany
    @JoinTable(name = "MENU",
            joinColumns = {@JoinColumn(name = "RESTAURANT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MEAL_ID")})
    @Column(name = "MEAL_ID")
    private List<Meal> menu;

    @JsonIgnore
    @OneToMany
    @JoinTable(name = "RESTAURANT_WORKERS",
            joinColumns = {@JoinColumn(name = "RESTAURANT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORKER_ID")})
    @Column(name = "WORKER_ID")
    private List<RestaurantUser> workers;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "RESTAURANT_ORDERS", joinColumns = {@JoinColumn(name = "RESTAURANT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID")})
    private List<Order> orders;

    public Restaurant() {
    }

    public Restaurant(String name, String avgdeliverytime, Short minorderprice, Short deliveryprice,
                      ShoppingHours hours) {
        this.name = name;
        this.avgdeliverytime = avgdeliverytime;
        this.minorderprice = minorderprice;
        this.deliveryprice = deliveryprice;
        this.hours = hours;
    }

    public void printmenu() {
        for (Meal meal : menu) {
            System.out.println("Meal name: " + meal.getName() + " Price: " + meal.getPrice() + " Comment: " + meal.getComment());
        }
    }
}
