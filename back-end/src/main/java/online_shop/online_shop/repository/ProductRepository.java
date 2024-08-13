package online_shop.online_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import online_shop.online_shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String description);

    Optional<Product> findByNameAndDescription(String name, String description);

}
