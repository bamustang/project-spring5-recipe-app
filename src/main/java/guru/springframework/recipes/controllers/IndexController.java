package guru.springframework.recipes.controllers;
import guru.springframework.recipes.repository.CategoryRepository;
import guru.springframework.recipes.repository.RecipeRepository;
import guru.springframework.recipes.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final RecipeRepository recipeRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/","/index","/index.htm", "/index.html"})
    public String showIndexPage (Model model) {
        log.info ("\n>> indexController (log)");
        System.out.println("  >> indexController (println)");
        model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        log.info ("\n<< indexController  returning 'index'");
        return("index");

    }


}
