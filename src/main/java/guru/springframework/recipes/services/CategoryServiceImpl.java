package guru.springframework.recipes.services;

import guru.springframework.recipes.model.Category;
import guru.springframework.recipes.repository.CategoryRepository;

import java.util.HashSet;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {
        Iterable<Category> categories;
        Set<Category> categorySet = new HashSet<>();
        categories = categoryRepository.findAll();
        categories.iterator().forEachRemaining(category -> {
            categorySet.add(category);
        });
        return categorySet;
    }
}
