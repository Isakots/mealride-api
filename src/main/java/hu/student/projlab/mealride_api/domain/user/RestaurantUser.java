package hu.student.projlab.mealride_api.domain.user;

import hu.student.projlab.mealride_api.domain.Restaurant;

import javax.persistence.*;

@Entity
@Table(name="RESTAURANT_USER")
public class RestaurantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne(mappedBy = "restaurantUser")
    private SpringUser springUser;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID")
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
