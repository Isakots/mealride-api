package hu.student.projlab.mealride_api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.converter.LocalDateTimeAttributeConverter;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CUSTOMER_ORDERS", joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CUSTOMER_ID")})
    private CustomerUser customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "RESTAURANT_ORDERS", joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RESTAURANT_ID")})
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_MEALS", joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CARTITEM_ID")})
    private List<CartItem> meals;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private DeliveryAddress address;
    @OneToOne
    @JoinColumn(name = "CARD_ID")
    private CreditCard card;
    @Column(name = "PRICE")
    private int price;
    @JsonIgnore
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "ORDER_TIME")
    private LocalDateTime ordertime;
    @Column(name = "COURIER_NAME")
    private String couriername;
    @Column(name = "CUSTOMER_COMMENT")
    private String usercomment;
    @Column(name = "WORKER_COMMENT")
    private String restaurantcomment;
    @Column(name = "STATUS")
    private Status status;  // enum type State


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerUser getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerUser customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<CartItem> getMeals() {
        return meals;
    }

    public void setMeals(List<CartItem> meals) {
        this.meals = meals;
    }

    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(LocalDateTime ordertime) {
        this.ordertime = ordertime;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    public String getRestaurantcomment() {
        return restaurantcomment;
    }

    public void setRestaurantcomment(String restaurantcomment) {
        this.restaurantcomment = restaurantcomment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
