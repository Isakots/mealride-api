package hu.student.projlab.mealride_api.service.dto;

import hu.student.projlab.mealride_api.domain.CartItem;
import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.domain.Restaurant;
import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    public List<CartItem> getMeals() {
        return meals;
    }

    public void setMeals(List<CartItem> meals) {
        this.meals = meals;
    }
}
