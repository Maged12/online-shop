package online_shop.online_shop.service;

import online_shop.online_shop.dto.ProductRequestDto;
import online_shop.online_shop.dto.response.ProductResponseDto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productDto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto productDto);

    ProductResponseDto updateProductImage(Long id, MultipartFile image);

    void deleteProduct(Long id);
}
