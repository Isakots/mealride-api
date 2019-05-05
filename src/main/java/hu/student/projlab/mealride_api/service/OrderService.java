package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.CartItem;
import hu.student.projlab.mealride_api.domain.CreditCard;
import hu.student.projlab.mealride_api.domain.Order;
import hu.student.projlab.mealride_api.domain.Status;
import hu.student.projlab.mealride_api.exception.OrderException;
import hu.student.projlab.mealride_api.repository.CreditCardRepository;
import hu.student.projlab.mealride_api.repository.OrderRepository;
import hu.student.projlab.mealride_api.service.dto.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    /*
        The task here is:
        1. Validate if user has the specified creditcard
        2. Set customer to order
        3. Set restaurant to order
        4. Set meals, card, address, usercomment to order
        5. Calculate and set full price to order
        6. Set Date to order
        7. Fill the price on OrderDetails then return
     */
    public OrderDetails processOrder(OrderDetails orderDetails) throws OrderException {
        if (!validateCard(orderDetails.getCard()))
            throw new OrderException("Credit card is not valid.");

        int price = calculateOrderPrice(orderDetails.getMeals());
        orderDetails.setPrice(price);
        Order order = createOrder(orderDetails);
        orderRepository.save(order);

        return orderDetails;
    }

    private boolean validateCard(CreditCard card) throws OrderException {
        return creditCardRepository.findAllByCustomerId(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getCustomerUser().getId())
                .orElseThrow(() -> new OrderException("Credit Card is not valid"))
                .stream()
                .anyMatch(creditcard -> creditcard.equals(card));
    }

    private int calculateOrderPrice(List<CartItem> meals) {
        // TODO Test if this is working
        return meals.stream()
                .mapToInt(cartitem -> cartitem.getAmount() * cartitem.getMeal().getPrice())
                .sum();
    }

    private Order createOrder(OrderDetails orderDetails) {
        Order order = new Order();
        order.setCustomer(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .getCustomerUser());
        order.setRestaurant(orderDetails.getRestaurant());
        order.setMeals(orderDetails.getMeals());
        order.setAddress(orderDetails.getAddress());
        order.setCard(orderDetails.getCard());
        order.setPrice(orderDetails.getPrice());
        order.setOrdertime(LocalDateTime.now());
        order.setUsercomment(orderDetails.getUsercomment());
        order.setStatus(Status.ORDERED);
        return order;
    }


    public List<OrderDetails> findUserOrders() {
        // TODO write an SQL query for this
        return Collections.emptyList();
    }

    public List<OrderDetails> findRestaurantOrders() {
        // TODO write an SQL query for this
        return Collections.emptyList();
    }
}
