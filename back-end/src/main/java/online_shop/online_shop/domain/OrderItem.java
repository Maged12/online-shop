package online_shop.online_shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderItems")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double price;
    @OneToOne
    private Product product;
    @ManyToOne
    private Order order;

    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + ", product=" + product;
    }

}
