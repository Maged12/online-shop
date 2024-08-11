package online_shop.online_shop.service;

import java.util.List;

import online_shop.online_shop.dto.CategoryResponseDto;
import online_shop.online_shop.dto.request.CategoryRequestDto;

public interface CategoryService {
    void createCategory(CategoryRequestDto categoryDTO);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();

    void updateCategory(Long id, CategoryResponseDto categoryDTO);

    void deleteCategory(Long id);
}
