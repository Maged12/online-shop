package online_shop.online_shop.repository;

import online_shop.online_shop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteAllByProductId(Long productId);
    List<OrderItem> findByOrderId(Long orderId);
}
