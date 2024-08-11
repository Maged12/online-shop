package online_shop.online_shop.adapter;

import java.util.List;
import java.util.stream.Collectors;

import online_shop.online_shop.domain.Category;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.ProductRequestDto;
import online_shop.online_shop.dto.response.ProductResponseDto;

public class ProductAdapter {

    public static ProductResponseDto getProductDtoFromProduct(Product product) {
        if (product == null)
            return null;

        ProductResponseDto productDto = new ProductResponseDto(product.getId(), product.getName(),
                product.getDescription(),
                product.getPrice(), CategoryAdapter.getProductCategoryResponseDtoFromCategory(product.getCategory()),
                product.getImageUrl());

        return productDto;
    }

    public static ProductResponseDto getProductDtoWithoutCategory(Product product) {
        if (product == null)
            return null;

        ProductResponseDto productDto = new ProductResponseDto(product.getId(), product.getName(),
                product.getDescription(),
                product.getPrice(), null,
                product.getImageUrl());
        return productDto;
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
        product.setImageUrl(imageUrl);

        return product;
    }

    public static List<ProductResponseDto> getProductDtoListFromProductList(List<Product> productList) {
        return productList.stream()
                .map(ProductAdapter::getProductDtoFromProduct)
                .collect(Collectors.toList());
    }

}