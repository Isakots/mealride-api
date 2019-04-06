package hu.student.projlab.mealride_api.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride_api.domain.Order;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="USER")
public class CustomerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="USER_ID")
    private Long id;

    @OneToOne(mappedBy = "customerUser")
    private SpringUser springUser;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ORDERS", joinColumns = { @JoinColumn(name="CUSTOMER_ID")},
            inverseJoinColumns = { @JoinColumn(name="ORDER_ID")})
    private List<Order> orders;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerUser customerUser = (CustomerUser) o;
        return Objects.equals(id, customerUser.id) &&
                Objects.equals(password, customerUser.password) &&
                Objects.equals(email, customerUser.email);
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
