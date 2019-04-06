package hu.student.projlab.mealride_api.domain.user;

import javax.persistence.*;

@Entity
@Table(name="COURIER_USER")
public class CourierUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne(mappedBy = "courierUser")
    private SpringUser springUser;

    // deliveries

    // previous deliveries

    // payments

    // etc

}
