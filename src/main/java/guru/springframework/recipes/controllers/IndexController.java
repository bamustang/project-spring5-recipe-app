package guru.springframework.recipes.controllers;
import guru.springframework.recipes.services.CategoryService;
import guru.springframework.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    private final CategoryService categoryService;
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService, CategoryService categoryService ) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping({"", "/","/index","/index.htm", "/index.html"})
    public String showIndexPage (Model model) {
        log.info (">> indexController (log)");
        model.addAttribute("recipes", recipeService.getRecipes());
        model.addAttribute("categories", categoryService.getCategories());
        log.info ("<< indexController  returning 'index'");
        return("index");
    }
}
