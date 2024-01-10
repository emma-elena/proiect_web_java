package Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Order {
    private Long id;

    private Integer order_number;
    private java.util.Date orderDate;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

}
