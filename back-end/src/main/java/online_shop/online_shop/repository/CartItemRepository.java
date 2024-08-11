// package online_shop.online_shop.repository;

// import online_shop.online_shop.domain.Cart;
// import online_shop.online_shop.domain.CartItem;
// import online_shop.online_shop.domain.Product;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface CartItemRepository extends JpaRepository<CartItem, Long> {
// CartItem findByProduct(Product product);
// void deleteAllByProductId(Long id);
// List<CartItem> findByCartId(Long cartId);

// }
