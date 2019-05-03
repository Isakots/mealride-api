package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.exception.OrderException;
import hu.student.projlab.mealride_api.service.OrderService;
import hu.student.projlab.mealride_api.service.dto.OrderDetails;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(EndpointConstants.ORDER_ENDPOINT)
class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<OrderDetails> processOrder(
            @RequestBody @Valid OrderDetails orderDetails) throws OrderException {
        OrderDetails details = orderService.processOrder(orderDetails);
        return ResponseEntity.ok(details);
    }

}
