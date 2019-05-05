package hu.student.projlab.mealride_api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.converter.LocalDateTimeAttributeConverter;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {

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
    private Status status;

}
