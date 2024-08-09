package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;



public class ProductAdapter {

    public static ProductDto getProductDtoFromProduct(Product product) {
        if (product == null) return null;

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        // Set the categoryDto separately to avoid cyclic dependency
        productDto.setCategoryDto(CategoryAdapter.getCategoryDtoWithoutProducts(product.getCategory()));

        return productDto;
    }

    public static ProductDto getProductDtoWithoutCategory(Product product) {
        if (product == null) return null;

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        // Do not set categoryDto to avoid cyclic dependency
        return productDto;
    }

    public static Product getProductFromProductDto(ProductDto productDto) {
        if (productDto == null) return null;
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(CategoryAdapter.getCategoryFromCategoryDto(productDto.getCategoryDto()));
        product.setImage(productDto.getImage());

        return product;
    }

    public static List<Product> getProductListFromProductDtoList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(ProductAdapter::getProductFromProductDto)
                .collect(Collectors.toList());
    }

    public static List<ProductDto> getProductDtoListFromProductList(List<Product> productList) {
        return productList.stream()
                .map(ProductAdapter::getProductDtoFromProduct)
                .collect(Collectors.toList());
    }

}