package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.ProductDto;
import online_shop.online_shop.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import static online_shop.online_shop.adapter.CategoryAdapter.getCategoryDtoFromCategory;
import static online_shop.online_shop.adapter.CategoryAdapter.getCategoryFromCategoryDto;

public class ProductAdapter {
    public static Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product.setCategory(getCategoryFromCategoryDto(productDto.getCategoryDto()));
        product.setImage(productDto.getImage());
        return product;
    }

    public static ProductDto getProductDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryDto(getCategoryDtoFromCategory(product.getCategory()));
        productDto.setImage(product.getImage());
        return productDto;
    }

    public static List<Product> getProductListFromProductDtoList(List<ProductDto> productDtoList) {
        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
            productList.add(getProductFromProductDto(productDto));
        }
        return productList;
    }

    public static List<ProductDto> getProductDtoListFromProductList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            productDtoList.add(getProductDtoFromProduct(product));
        }
        return productDtoList;
    }
}
