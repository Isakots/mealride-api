package hu.student.projlab.mealride_api.order;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.creditcard.CreditCard;
import hu.student.projlab.mealride_api.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride_api.restaurant.Restaurant;
import hu.student.projlab.mealride_api.user.User;

import javax.persistence.*;

@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ORDERS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="CUSTOMER_ID")})
    private User customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="RESTAURANT_ORDERS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="RESTAURANT_ID")})
    private Restaurant restaurant;

   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="ORDER_MEALS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="CARTITEM_ID")})
    private List<CartItem> meals;*/

    @OneToOne
    @JoinColumn(name="ADDRESS_ID")
    private DeliveryAddress address;
    @OneToOne
    @JoinColumn(name="CARD_ID")
    private CreditCard card;
    @Column(name="PRICE")
    private int price;
    @Column(name="ORDER_TIME")
    private Long datetime;
    @Column(name="COURIER_NAME")
    private String couriername;
    @Column(name="CUSTOMER_COMMENT")
    private String usercomment;
    @Column(name="WORKER_COMMENT")
    private String restaurantcomment;
   // private State state;  // enum type State


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /*public List<CartItem> getMeals() {
        return meals;
    }

    public void setMeals(List<CartItem> meals) {
        this.meals = meals;
    }*/

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

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {

        this.datetime = datetime;
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

}
