package guru.springframework.recipes.controllers;

import guru.springframework.recipes.model.Recipe;
import guru.springframework.recipes.services.CategoryService;
import guru.springframework.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @Test
    void testMocMvc() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"));

    }
}