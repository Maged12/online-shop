package online_shop.online_shop.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import online_shop.online_shop.domain.Category;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.CategoryResponseDto;
import online_shop.online_shop.dto.request.CategoryRequestDto;
import online_shop.online_shop.dto.response.CategoryProductResponseDto;
import online_shop.online_shop.dto.response.ProductCategoryResponseDto;

public class CategoryAdapter {

    public static CategoryResponseDto getCategoryResponseDtoFromCategory(Category category) {
        if (category == null)
            return null;

        List<CategoryProductResponseDto> productDtos = new ArrayList<>();

        if (category.getProducts() != null) {
            productDtos = category.getProducts().stream()
                    .map(product -> CategoryAdapter.getCategoryProductResponseDtoFromProduct(product)).toList();
        }
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category.getName(), category.getDescription(),
                productDtos);

        return categoryResponseDto;
    }

    public static CategoryProductResponseDto getCategoryProductResponseDtoFromProduct(Product product) {
        if (product == null)
            return null;

        return new CategoryProductResponseDto(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getImageUrl());
    }

    public static Category getCategoryFromCategoryRequestDto(CategoryRequestDto categoryDto) {
        if (categoryDto == null)
            return null;

        Category category = new Category();
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());

        return category;
    }

    public static List<CategoryResponseDto> getCategoryDtoListFromCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(CategoryAdapter::getCategoryResponseDtoFromCategory)
                .collect(Collectors.toList());
    }

    public static ProductCategoryResponseDto getProductCategoryResponseDtoFromCategory(Category category) {
        if (category == null)
            return null;

        final ProductCategoryResponseDto productCategoryResponseDto = new ProductCategoryResponseDto(category.getName(),
                category.getDescription());

        return productCategoryResponseDto;
    }
}