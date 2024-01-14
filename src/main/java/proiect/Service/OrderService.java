package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.OrderEntity;
import proiect.Mapper.OrderMapper;
import proiect.Micunelte.Order;
import proiect.Repository.OrderRepository;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderMapper mapper;

    public Order createOrder(Order order){
        repository.save(mapper.toEntity(order));
        Optional<OrderEntity> orderEntity= repository.findByOrderNumber(order.getOrderNumber());
        return mapper.toResponse(orderEntity.get());

    }
}
