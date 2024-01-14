package proiect.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.Entity.OrderEntity;
import proiect.Entity.UserEntity;
import proiect.Exceptions.UserNotFound;
import proiect.Micunelte.Order;
import proiect.Repository.UserRepository;

@Component
public class OrderMapper {
    @Autowired
    UserRepository repository;

    public Order toResponse(OrderEntity order) {
        Order response = new Order();

        response.setOrderNumber(order.getOrderNumber());
        response.setOrderDate(order.getOrderDate());

        return response;
    }

    public OrderEntity toEntity(Order order ,String username) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderNumber(order.getOrderNumber());
        orderEntity.setOrderDate(order.getOrderDate());

        UserEntity user = repository.findByUsername(username).orElseThrow(() -> new UserNotFound("User not found for username"+username));

        orderEntity.setUser(user);

        final var orders = user.getOrders();
        orders.add(orderEntity);
        user.setOrders(orders);
        repository.save(user);

        return orderEntity;
    }
}
