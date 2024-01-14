package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.Micunelte.CreateOrder;
import proiect.Micunelte.Order;
import proiect.Service.OrderService;
import proiect.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody final CreateOrder order) {

        final boolean isUserCustomer = userService.checkUserCustomer(order.getUsername());

        if (isUserCustomer) {
            final var savedOrder = service.createOrder(order.getOrder(), order.getUsername());
            return ResponseEntity.ok(savedOrder);
        }
        return (ResponseEntity<Order>) ResponseEntity.status(401);
    }
}
