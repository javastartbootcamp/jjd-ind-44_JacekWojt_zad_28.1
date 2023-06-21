package pl.javastart.restoffers.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Metoda zwraca listę nazw wszystkich kategorii
    @GetMapping("/api/categories/names")
    public List<String> findAllCategories() {
        return categoryService.findAllCategories();
    }

    //Metoda zwraca listę wszystkich kategorii z liczbą ofert
    @GetMapping("/api/categories")
    public List<CategoryDto> findAllCategoriesWithOfferNumber() {
        return categoryService.findAllCategoriesWithOfferNumber();
    }

    //Usuwanie kategorii po id
    @DeleteMapping("/api/categories/{id}")
    public void deleteCategoriesById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    //Metoda dodaje nową categorie
    @PostMapping("/api/categories")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryInsertDto categoryInsertDto) {
        return ResponseEntity.ok(categoryService.insertNewCategory(categoryInsertDto));
    }
}
