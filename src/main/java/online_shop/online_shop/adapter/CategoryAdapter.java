package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Category;
import online_shop.online_shop.dto.CategoryDto;
import online_shop.online_shop.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryAdapter {

   public static CategoryDto getCategoryDtoFromCategory(Category category) {
        if (category == null) return null;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        // Avoid setting categoryDto in productDtos to prevent cyclic dependency
        List<ProductDto> productDtos = category.getProducts().stream()
                .map(product -> {
                    ProductDto productDto = ProductAdapter.getProductDtoWithoutCategory(product);
                    //productDto.setCategoryDto(null); // Avoid cyclic dependency
                    return productDto;
                }).collect(Collectors.toList());

        categoryDto.setProductDtos(productDtos);
        return categoryDto;
    }

    public static Category getCategoryFromCategoryDto(CategoryDto categoryDto) {
        if (categoryDto == null) return null;

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    public static List<Category> getCategoryListFromCategoryDtoList(List<CategoryDto> categoryDtoList) {
        return categoryDtoList.stream()
                .map(CategoryAdapter::getCategoryFromCategoryDto)
                .collect(Collectors.toList());
    }

    public static List<CategoryDto> getCategoryDtoListFromCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(CategoryAdapter::getCategoryDtoFromCategory)
                .collect(Collectors.toList());
    }
  public static CategoryDto getCategoryDtoWithoutProducts(Category category) {
        if (category == null) return null;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}