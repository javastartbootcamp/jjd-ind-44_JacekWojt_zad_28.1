package pl.javastart.restoffers.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public Category insertNewCategory(CategoryInsertDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return category;
    }

    public List<CategoryDto> findAllCategoriesWithOfferNumber() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryService::toDto)
                .collect(Collectors.toList());
    }

    public void deleteCategoryById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            //ignore
        }
    }

    private static CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setOffers(category.getOffers().size());
        return dto;
    }
}

