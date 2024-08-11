package online_shop.online_shop.service;

import online_shop.online_shop.dto.ProductRequestDto;
import online_shop.online_shop.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    void updateProduct(Long id, ProductRequestDto productDto);

    void deleteProduct(Long id);
}
