package online_shop.online_shop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.CategoryAdapter;
import online_shop.online_shop.domain.Category;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.CategoryResponseDto;
import online_shop.online_shop.dto.request.CategoryRequestDto;
import online_shop.online_shop.repository.CategoryRepository;
import online_shop.online_shop.repository.OrderItemRepository;
import online_shop.online_shop.repository.ProductRepository;
import online_shop.online_shop.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    // @Autowired
    // CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category categoryEntity = CategoryAdapter.getCategoryFromCategoryRequestDto(categoryRequestDto);
        var category = categoryRepository.save(categoryEntity);

        return CategoryAdapter.getCategoryResponseDtoFromCategory(category);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return CategoryAdapter.getCategoryResponseDtoFromCategory(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return CategoryAdapter.getCategoryDtoListFromCategoryList(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.description());
        var updatedCategory = categoryRepository.save(category);
        return CategoryAdapter.getCategoryResponseDtoFromCategory(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Delete all cart items associated with products in this category
        for (Product product : category.getProducts()) {

            orderItemRepository.deleteAllByProductId(product.getId());
        }

        // Delete all products in this category
        productRepository.deleteAll(category.getProducts());

        // Finally, delete the category
        categoryRepository.delete(category);
    }
}
