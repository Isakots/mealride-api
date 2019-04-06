package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;

import javax.persistence.*;
import java.util.List;


@Entity
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="RESTAURANT_NAME")
    private String name;
    @Column(name="AVERAGE_DELIVERY_TIME")
    private String avgdeliverytime;
    @Column(name="MINIMUM_ORDER_PRICE")
    private Short minorderprice;
    @Column(name="DELIVERY_PRICE")
    private Short deliveryprice;

    @Embedded
    private ShoppingHours hours;

    @ElementCollection
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
    private List<CustomerUser> workers;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="RESTAURANT_ORDERS", joinColumns = { @JoinColumn(name="RESTAURANT_ID")},
            inverseJoinColumns = { @JoinColumn(name="ORDER_ID")})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvgdeliverytime() {
        return avgdeliverytime;
    }

    public void setAvgdeliverytime(String avgdeliverytime) {
        this.avgdeliverytime = avgdeliverytime;
    }

    public Short getMinorderprice() {
        return minorderprice;
    }

    public void setMinorderprice(Short minorderprice) {
        this.minorderprice = minorderprice;
    }

    public Short getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(Short deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public ShoppingHours getHours() {
        return hours;
    }

    public void setHours(ShoppingHours hours) {
        this.hours = hours;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void setMenu(List<Meal> menu) {
        this.menu = menu;
    }

    public List<CustomerUser> getWorkers() {
        return workers;
    }

    public void setWorkers(List<CustomerUser> workers) {
        this.workers = workers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void printmenu() {
        for(Meal meal: menu) {
            System.out.println("Meal name: "+ meal.getName()+" Price: "+ meal.getPrice()+" Comment: "+meal.getComment());
        }
    }
}
