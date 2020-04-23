package guru.springframework.recipes.controllers;
import guru.springframework.recipes.repository.CategoryRepository;
import guru.springframework.recipes.repository.RecipeRepository;
import guru.springframework.recipes.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository uomRepository;
    private RecipeRepository recipeRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/","/index","/index.htm", "/index.html"})
    public String showIndexPage (Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return("index");


    }


}
