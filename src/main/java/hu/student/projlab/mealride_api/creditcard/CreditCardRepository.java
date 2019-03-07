package hu.student.projlab.mealride_api.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

     Optional<List<CreditCard>> findAllByUserId(Long userId);

     CreditCard getBankCardByNumber(Long number);
}
