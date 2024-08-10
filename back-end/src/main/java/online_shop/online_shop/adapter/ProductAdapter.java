package online_shop.online_shop.adapter;

import java.util.List;
import java.util.stream.Collectors;

import online_shop.online_shop.domain.Category;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.ProductRequestDto;
import online_shop.online_shop.dto.ProductResponseDto;

public class ProductAdapter {

    public static ProductResponseDto getProductDtoFromProduct(Product product) {
        if (product == null)
            return null;

        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        // Set the categoryDto separately to avoid cyclic dependency
        productDto.setCategoryDto(CategoryAdapter.getCategoryDtoWithoutProducts(product.getCategory()));

        return productDto;
    }

    public static ProductResponseDto getProductDtoWithoutCategory(Product product) {
        if (product == null)
            return null;

        ProductResponseDto productDto = new ProductResponseDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        // Do not set categoryDto to avoid cyclic dependency
        return productDto;
    }

    public static Product getProductFromProductDto(ProductResponseDto productDto) {
        if (productDto == null)
            return null;
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(CategoryAdapter.getCategoryFromCategoryDto(productDto.getCategoryDto()));
        product.setImage(productDto.getImage());

        return product;
    }

    public static Product getProductFromProductRequsetDto(ProductRequestDto productDto, String imageUrl) {
        if (productDto == null)
            return null;
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        var category = new Category();
        category.setId(Long.parseLong(productDto.categoryId()));
        product.setCategory(category);
        product.setImage(imageUrl);

        return product;
    }

    public static List<Product> getProductListFromProductDtoList(List<ProductResponseDto> productDtoList) {
        return productDtoList.stream()
                .map(ProductAdapter::getProductFromProductDto)
                .collect(Collectors.toList());
    }

    public static List<ProductResponseDto> getProductDtoListFromProductList(List<Product> productList) {
        return productList.stream()
                .map(ProductAdapter::getProductDtoFromProduct)
                .collect(Collectors.toList());
    }

}