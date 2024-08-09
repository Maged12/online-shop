package online_shop.online_shop.repository;

import online_shop.online_shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String description);
    Optional<Product> findByNameAndDescription(String name, String description);


}
