package guru.springframework.recipes.services;

import guru.springframework.recipes.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
