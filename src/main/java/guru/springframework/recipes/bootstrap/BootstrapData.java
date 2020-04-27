package guru.springframework.recipes.bootstrap;

import guru.springframework.recipes.model.*;
import guru.springframework.recipes.repository.CategoryRepository;
import guru.springframework.recipes.repository.RecipeRepository;
import guru.springframework.recipes.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class BootstrapData implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapData(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run (String... args) throws Exception {
        log.info("Starting BoostrapData.run method...");
        log.info("\nEnabled loggers: \nlog.info: " + log.isInfoEnabled()
                + "\nlog.warning: " + log.isWarnEnabled()
                + "\nlog.debug: " + log.isDebugEnabled()
                + "\nlog.trace: " + log.isTraceEnabled());
        log.info("Starting BoostrapData.run method... (log.info)");
        log.debug("Starting BoostrapData.run method... (log.debug)");
        log.trace("Starting BoostrapData.run method... (log.trace)");

        //categories must already be in the table
        Set<Category>recipeCategories = new HashSet<>();
        Category categoryTexMex =  categoryRepository.findByCategory("Tex-Mex").orElse(null);
        if (categoryTexMex != null) recipeCategories.add(categoryTexMex);
        Category categoryMexican = categoryRepository.findByCategory("Mexican").orElse( null);
        if (categoryMexican != null) recipeCategories.add(categoryMexican);

        //units of measure must already be in the table
        Optional<UnitOfMeasure> tspOptional = unitOfMeasureRepository.findByUnitOfMeasure("teaspoon");
        if (!tspOptional.isPresent()) {throw new RuntimeException("'teaspoon' value missing from UOM");}
        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByUnitOfMeasure("tablespoon");
        if (!tablespoonOptional.isPresent()) {throw new RuntimeException("'tablespoon' value missing from UOM");}
        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByUnitOfMeasure("tablespoon");
        if (!pintOptional.isPresent()) {throw new RuntimeException("'pint' value missing from UOM");}
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByUnitOfMeasure("pinch");
        if (!pinchOptional.isPresent()) {throw new RuntimeException("'pinch' value missing from UOM");}
        Optional<UnitOfMeasure> garnishOptional = unitOfMeasureRepository.findByUnitOfMeasure("garnish");
        if (!garnishOptional.isPresent()) {throw new RuntimeException("'garnish' value missing from UOM");}
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByUnitOfMeasure("each");
        if (!eachOptional.isPresent()) {throw new RuntimeException("'each' value missing from UOM");}
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByUnitOfMeasure("cup");
        if (!cupOptional.isPresent()) {throw new RuntimeException("'cup' value missing from UOM");}
        Optional<UnitOfMeasure> poundOptional = unitOfMeasureRepository.findByUnitOfMeasure("pound");
        if (!poundOptional.isPresent()) {throw new RuntimeException("'pound' value missing from UOM");}

        // Guacamole recipe -----------------------------------------
        Recipe guacamole = new Recipe();
        Set<Ingredient> ingredientSetGuacamole = new HashSet<>();
        UnitOfMeasure uom = new UnitOfMeasure();

        //Ingredients
        ingredientSetGuacamole.add( new Ingredient("Ripe avocados", BigDecimal.valueOf(2.0), eachOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("salt", BigDecimal.valueOf(0.25), tspOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("fresh lime juice", BigDecimal.ONE, tablespoonOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("minced red onion", BigDecimal.valueOf(2), cupOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("Serrano chile, stems and seeds removed, minced",
                BigDecimal.ONE, eachOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("cilantro (leaves and tender stems) finely chopped",
                BigDecimal.valueOf(2), tablespoonOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("fine ground black pepper", BigDecimal.ONE, pinchOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("ripe tomato", BigDecimal.valueOf(0.25), eachOptional.get(), guacamole ));
        ingredientSetGuacamole.add(new Ingredient("garnish with radish or jicama", BigDecimal.ONE, garnishOptional.get(), guacamole ));

        log.debug("All guacamole ingredients defined and added to the ingredient set. (log.debug)");

        //populate the recipe
        guacamole.setIngredients(ingredientSetGuacamole);

        StringBuilder guacamoleDirections = new StringBuilder();
        guacamoleDirections.append("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n")
                .append("\n")
                .append("2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n")
                .append("\n")
                .append("3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n")
                .append("\n")
                .append("Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n")
                .append("\n")
                .append("Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n")
                .append("\n")
                .append("Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n")
                .append("\n")
                .append("4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        guacamole.setDirections(guacamoleDirections.toString());
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setServings(4);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setCategories(recipeCategories);

        recipeRepository.save(guacamole);
        log.info(String.format("recipe guacamole saved to recipe repository. There are %s recipes in the repository.",recipeRepository.count()));

        // Chicken  taco recipe -----------------------------------------
        Recipe tacos = new Recipe();
        Set<Ingredient>tacoIngredientSet = new HashSet<>();

        tacoIngredientSet.add(new Ingredient("ancho chile", BigDecimal.valueOf(2), tablespoonOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("dried oregano", BigDecimal.ONE, tspOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("cumin", BigDecimal.ONE, tspOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("sugar", BigDecimal.ONE, tspOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("salt", BigDecimal.valueOf(0.5), tspOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("clove garlic, finely chopped", BigDecimal.ONE, eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("finely grated orange zest", BigDecimal.ONE, tablespoonOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("fresh squeezed orange juice", BigDecimal.valueOf(3), tablespoonOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("olive oil", BigDecimal.valueOf(2), tablespoonOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("skinless, boneless chicken thighs", BigDecimal.valueOf(1.25), poundOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("small corn tortillas", BigDecimal.valueOf(8), eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("packed baby arugula", BigDecimal.valueOf(3), cupOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4), eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2), eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("cherry tomatoes, halved", BigDecimal.valueOf(2), cupOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(0.25), eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("roughly chopped cilantro", BigDecimal.ONE, eachOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("sour cream", BigDecimal.valueOf(0.5), cupOptional.get(), tacos));
        tacoIngredientSet.add(new Ingredient("lime, cut into wedges", BigDecimal.ONE, eachOptional.get(), tacos));
        tacos.setIngredients(tacoIngredientSet);
        tacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        tacos.setServings(6);
        tacos.setPrepTime(30);
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        );
        tacos.setCategories(recipeCategories);
        recipeRepository.save(tacos);

        log.info(String.format("recipe tacos saved to recipe repository. There are %s recipes in the repository.",recipeRepository.count()));
        log.info("\n\nBootstrapData completed.");
    }
}
