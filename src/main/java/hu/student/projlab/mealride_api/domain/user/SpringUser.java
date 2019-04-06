package hu.student.projlab.mealride_api.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BASIC_USER")
public class SpringUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name="USERNAME")
    private String username;
    @JsonIgnore
    @Column(name="PASSWORD")
    private String password; //hash

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLES", joinColumns = { @JoinColumn(name="USER_ID")},
            inverseJoinColumns = { @JoinColumn(name="ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private CustomerUser customerUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private RestaurantUser restaurantUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private CourierUser courierUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    public RestaurantUser getRestaurantUser() {
        return restaurantUser;
    }

    public void setRestaurantUser(RestaurantUser restaurantUser) {
        this.restaurantUser = restaurantUser;
    }

    public CourierUser getCourierUser() {
        return courierUser;
    }

    public void setCourierUser(CourierUser courierUser) {
        this.courierUser = courierUser;
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
}
