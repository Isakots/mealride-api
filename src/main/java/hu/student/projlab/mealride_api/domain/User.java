package hu.student.projlab.mealride_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="USER_ID")
    private Long id;
    @Column(name="FIRSTNAME")
    private String firstname;
    @Column(name="LASTNAME")
    private String lastname;
    @JsonIgnore
    @Column(name="PASSWORD")
    private String password; //hash
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE")
    private String phone;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLES", joinColumns = { @JoinColumn(name="USER_ID")},
                inverseJoinColumns = { @JoinColumn(name="ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ORDERS", joinColumns = { @JoinColumn(name="CUSTOMER_ID")},
            inverseJoinColumns = { @JoinColumn(name="ORDER_ID")})
    private List<Order> orders;

    public User() {}

    public User(String firstname, String lastname, String password, String email, String phone, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String rolesToString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Role role: roles) {
            stringBuilder.append("Role: ");
            stringBuilder.append(role.getRole()+"\n");
        }
        return stringBuilder.toString();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
