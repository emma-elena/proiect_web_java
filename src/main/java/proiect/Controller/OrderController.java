package proiect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proiect.Micunelte.Order;
import proiect.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody final Order order) {
        final var savedOrder = service.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }
}
