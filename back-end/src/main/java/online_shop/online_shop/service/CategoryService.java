package online_shop.online_shop.service;

import java.util.List;

import online_shop.online_shop.dto.CategoryResponseDto;
import online_shop.online_shop.dto.request.CategoryRequestDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryDTO);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryDTO);

    void deleteCategory(Long id);
}
