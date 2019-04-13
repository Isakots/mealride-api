package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

     Optional<List<CreditCard>> findAllByCustomerUserId(Long userId);

     CreditCard getBankCardByNumber(Long number);
}
