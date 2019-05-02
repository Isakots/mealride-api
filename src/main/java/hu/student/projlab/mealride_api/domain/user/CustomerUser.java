package hu.student.projlab.mealride_api.domain.user;

import hu.student.projlab.mealride_api.domain.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CUSTOMER_USER")
public class CustomerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne(mappedBy = "customerUser")
    private SpringUser springuser;

    @Column(name="FIRSTNAME")
    private String firstname;
    @Column(name="LASTNAME")
    private String lastname;
    @Column(name="PHONE")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ORDERS", joinColumns = { @JoinColumn(name="CUSTOMER_ID")},
            inverseJoinColumns = { @JoinColumn(name="ORDER_ID")})
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpringUser getSpringuser() {
        return springuser;
    }

    public void setSpringuser(SpringUser springuser) {
        this.springuser = springuser;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
