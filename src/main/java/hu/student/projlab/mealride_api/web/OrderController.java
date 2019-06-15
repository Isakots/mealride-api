package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.exception.OrderException;
import hu.student.projlab.mealride_api.service.OrderService;
import hu.student.projlab.mealride_api.service.dto.OrderDetails;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(EndpointConstants.ORDER_ENDPOINT)
    public ResponseEntity<OrderDetails> processOrder(
            @RequestBody @Valid OrderDetails orderDetails) throws OrderException {
        OrderDetails details = orderService.processOrder(orderDetails);
        return ResponseEntity.ok(details);
    }

    @GetMapping(EndpointConstants.USER_ENDPOINT + EndpointConstants.PREVIOUS_ORDERS_ENDPOINT)
    public ResponseEntity<List<OrderDetails>> findUserPreviousOrders() {
        List<OrderDetails> previousOrders = orderService.findUserOrders();
        return ResponseEntity.ok(previousOrders);
    }

    @GetMapping(EndpointConstants.RESTAURANT_ENDPOINT + EndpointConstants.PREVIOUS_ORDERS_ENDPOINT)
    public ResponseEntity<List<OrderDetails>> findRestaurantPreviousOrders() {
        List<OrderDetails> previousOrders = orderService.findRestaurantOrders();
        return ResponseEntity.ok(previousOrders);
    }

}
