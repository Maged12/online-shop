package online_shop.online_shop.ServiceImpl;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.ProductAdapter;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.ProductDto;
import online_shop.online_shop.repository.ProductRepository;
import online_shop.online_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = ProductAdapter.getProductFromProductDto(productDto);
        productRepository.save(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductAdapter.getProductDtoFromProduct(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return ProductAdapter.getProductDtoListFromProductList(productRepository.findAll());
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategory(ProductAdapter.getProductFromProductDto(productDto).getCategory());
            product.setImage(productDto.getImage());
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

