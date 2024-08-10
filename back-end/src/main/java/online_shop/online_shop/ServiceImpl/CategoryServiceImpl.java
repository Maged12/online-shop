package online_shop.online_shop.ServiceImpl;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.CategoryAdapter;
import online_shop.online_shop.domain.Category;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.CategoryDto;
import online_shop.online_shop.repository.CartItemRepository;
import online_shop.online_shop.repository.CategoryRepository;
import online_shop.online_shop.repository.OrderItemRepository;
import online_shop.online_shop.repository.ProductRepository;
import online_shop.online_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;;

    @Override
    public void createCategory(CategoryDto categoryDTO) {
        Category category = CategoryAdapter.getCategoryFromCategoryDto(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return CategoryAdapter.getCategoryDtoFromCategory(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return CategoryAdapter.getCategoryDtoListFromCategoryList(categoryRepository.findAll());
    }

    @Override
    public void updateCategory(Long id, CategoryDto categoryDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        // Delete all cart items associated with products in this category
        for (Product product : category.getProducts()) {
            cartItemRepository.deleteAllByProductId(product.getId());
            orderItemRepository.deleteAllByProductId(product.getId());
        }

        // Delete all products in this category
        productRepository.deleteAll(category.getProducts());

        // Finally, delete the category
        categoryRepository.delete(category);
    }
}

