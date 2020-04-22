package guru.springframework.recipes.services;

import guru.springframework.recipes.model.Recipe;
import guru.springframework.recipes.repository.RecipeRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.addAll((Collection<? extends Recipe>) recipeRepository.findAll());
        return recipeSet;
    }
}
