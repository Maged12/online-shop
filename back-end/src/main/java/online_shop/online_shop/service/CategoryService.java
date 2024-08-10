package online_shop.online_shop.service;
import online_shop.online_shop.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDTO);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    void updateCategory(Long id, CategoryDto categoryDTO);
    void deleteCategory(Long id);
}
