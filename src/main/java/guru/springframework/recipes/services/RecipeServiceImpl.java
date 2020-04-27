package guru.springframework.recipes.services;

import guru.springframework.recipes.model.Recipe;
import guru.springframework.recipes.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.info (">> RecipeServiceImpl.getRecipes");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.addAll((Collection<? extends Recipe>) recipeRepository.findAll());
        log.info ("<< RecipeServiceImpl.getRecipes, returing Set<Recipes>");
        return recipeSet;
    }
}
