package online_shop.online_shop.repository;

import online_shop.online_shop.domain.Cart;
import online_shop.online_shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
