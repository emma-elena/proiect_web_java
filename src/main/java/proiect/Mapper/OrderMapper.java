package proiect.Mapper;

import org.springframework.stereotype.Component;
import proiect.Entity.OrderEntity;
import proiect.Micunelte.Order;

@Component
public class OrderMapper {
    public Order toResponse(OrderEntity order) {
        Order response = new Order();

        response.setOrderNumber(order.getOrderNumber());

        return response;
    }

    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderNumber(order.getOrderNumber());

        return orderEntity;
    }
}
