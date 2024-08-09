package online_shop.online_shop.service;

import online_shop.online_shop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    void updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
