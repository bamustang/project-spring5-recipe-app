package guru.springframework.recipes.services;

import guru.springframework.recipes.model.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();
}
