package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Category;
import online_shop.online_shop.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter {
    public static Category getCategoryFromCategoryDto(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    public static CategoryDto getCategoryDtoFromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public static List<Category> getCategoryListFromCategoryDtoList(List<CategoryDto> categoryDtoList) {
        List<Category> categoryList = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtoList) {
            categoryList.add(getCategoryFromCategoryDto(categoryDto));
        }
        return categoryList;
    }

    public static List<CategoryDto> getCategoryListFromCategoryList(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDtoList.add(getCategoryDtoFromCategory(category));
        }
        return categoryDtoList;
    }
}
