package guru.springframework.recipes.controllers;
import guru.springframework.recipes.repository.CategoryRepository;
import guru.springframework.recipes.repository.RecipeRepository;
import guru.springframework.recipes.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
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
    public String showIndexPage () {
        System.out.println(">>showIndexPage Handler for /, /index, /index.htm, /index.html");
        System.out.println("ID for tablespoon is "+uomRepository.findByUnitOfMeasure("tablespoon").get().getId());
        System.out.println("ID for Tex-Mex is "+categoryRepository.findByCategory("Tex-Mex").get().getId());
        System.out.println("Recipe count = "+recipeRepository.count());
        return("index");
    }


}
