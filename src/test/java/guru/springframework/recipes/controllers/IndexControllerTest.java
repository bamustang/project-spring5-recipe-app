package guru.springframework.recipes.controllers;

import guru.springframework.recipes.model.Recipe;
import guru.springframework.recipes.services.CategoryService;
import guru.springframework.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    CategoryService categoryService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService, categoryService);
    }

    @Test
    void showIndexPage() {

       assert (indexController.showIndexPage(model).equals("index"));
       verify(recipeService, times(1)).getRecipes();
    }
}