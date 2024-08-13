package online_shop.online_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online_shop.online_shop.dto.CategoryResponseDto;
import online_shop.online_shop.dto.request.CategoryRequestDto;
import online_shop.online_shop.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long id) {
        CategoryResponseDto categoryDto = categoryService.getCategoryById(id);
        if (categoryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto categoryDto) {
        var category = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        var categoryResponseDto = categoryService.updateCategory(id,
                categoryRequestDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
