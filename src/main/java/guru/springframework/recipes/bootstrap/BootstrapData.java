package guru.springframework.recipes.bootstrap;

import guru.springframework.recipes.model.*;
import guru.springframework.recipes.repository.CategoryRepository;
import guru.springframework.recipes.repository.RecipeRepository;
import guru.springframework.recipes.repository.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
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
    public void run (String... args) throws RuntimeException, Exception {
        System.out.println("Starting BoostrapData.run method...");

        //Process guacamole
        //initialize
        Recipe guacamole = new Recipe();
        Set<Ingredient> ingredientSetGuacamole = new HashSet<>();
        UnitOfMeasure uom = new UnitOfMeasure();
        Set<Category> guacamoleCategories = new HashSet<>();

        //categories must already be in the table
        Optional<Category> texMex = categoryRepository.findByCategory("Tex-Mex");
        Optional<Category> mexican = categoryRepository.findByCategory("Mexican");
        if (texMex.isPresent()) {
            guacamoleCategories.add(texMex.get());}
        if (mexican.isPresent()) {
            guacamoleCategories.add(mexican.get());}

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


        //Ingredients
        Ingredient avocados = new Ingredient();
        avocados.setAmount(BigDecimal.valueOf(2.0));
        avocados.setDescription("Ripe avocados");
        //uom - each
        avocados.setUom(eachOptional.get());
        ingredientSetGuacamole.add(avocados);

        Ingredient salt = new Ingredient();
        salt.setAmount(BigDecimal.valueOf(0.25));
        salt.setDescription("salt");
        //uom - tsp
        salt.setUom(tspOptional.get());
        ingredientSetGuacamole.add(salt);

        Ingredient limeJuice = new Ingredient();
        limeJuice.setAmount(BigDecimal.ONE);
        limeJuice.setDescription("fresh lime juice");
        //uom - TBSP
        limeJuice.setUom(tablespoonOptional.get());
        ingredientSetGuacamole.add(limeJuice);

        Ingredient redOnion = new Ingredient();
        redOnion.setAmount(BigDecimal.valueOf(2));
        redOnion.setDescription("minced red onion");
        //uom - cup
        redOnion.setUom(cupOptional.get());
        ingredientSetGuacamole.add(redOnion);

        Ingredient serranoChile = new Ingredient();
        serranoChile.setAmount(BigDecimal.ONE);
        serranoChile.setDescription("Serrano chile, stems and seeds removed, minced");
        //uom - Each
        serranoChile.setUom(eachOptional.get());
        ingredientSetGuacamole.add(serranoChile);

        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setDescription("cilantro (leaves and tender stems) finely chopped");
        //uom - TBSP
        cilantro.setUom(tablespoonOptional.get());
        ingredientSetGuacamole.add(cilantro);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setAmount(BigDecimal.valueOf(1));
        blackPepper.setDescription("fine ground black pepper");
        //uom - pinch
        blackPepper.setUom(pinchOptional.get());
        ingredientSetGuacamole.add(blackPepper);

        Ingredient tomato = new Ingredient();
        tomato.setAmount(BigDecimal.valueOf(0.25));
        tomato.setDescription("ripe tomato");
        //uom - Each
        tomato.setUom(eachOptional.get());
        ingredientSetGuacamole.add(tomato);

        Ingredient garnish = new Ingredient();
        garnish.setAmount(BigDecimal.valueOf(1));
        garnish.setDescription("garnish with raddish or jicama");
        //uom - garnish
        garnish.setUom(garnishOptional.get());
        ingredientSetGuacamole.add(garnish);

        System.out.println("All ingredients defined and added to the ingredient set.");

        //populate the recipe
        guacamole.setIngredients(ingredientSetGuacamole);
        guacamole.setCategories(guacamoleCategories);

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

        recipeRepository.save(guacamole);
        System.out.println(String.format("recipe guacamole saved to recipe repository. There are %s recipes in the repository.",recipeRepository.count()));

        // Chicken  taco recipe -----------------------------------------
        Recipe tacos = new Recipe();
        Set<Ingredient>tacoIngredientSet = new HashSet<>();

        Ingredient ancho = new Ingredient();
        ancho.newIngredient("ancho chile",BigDecimal.valueOf(2), tablespoonOptional.get());
        tacoIngredientSet.add(ancho);

        Ingredient oregano = new Ingredient();
        oregano.newIngredient("dried oregano", BigDecimal.ONE,tspOptional.get());
        tacoIngredientSet.add(oregano);

        Ingredient cumin = new Ingredient();
        cumin.newIngredient("dried oregano", BigDecimal.ONE,tspOptional.get());
        tacoIngredientSet.add(cumin);

        Ingredient sugar = new Ingredient();
        sugar.newIngredient("sugar", BigDecimal.ONE,tspOptional.get());
        tacoIngredientSet.add(sugar);

        Ingredient tacoSalt = new Ingredient();
        tacoSalt.newIngredient("salt", BigDecimal.valueOf(0.5),tspOptional.get());
        tacoIngredientSet.add(tacoSalt);

        Ingredient garlic = new Ingredient();
        garlic.newIngredient("clove garlic, finely chopped", BigDecimal.ONE,eachOptional.get());
        tacoIngredientSet.add(garlic);

        Ingredient orangeZest = new Ingredient();
        orangeZest.newIngredient("finely grated orange zest", BigDecimal.ONE,tablespoonOptional.get());
        tacoIngredientSet.add(orangeZest);

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.newIngredient("fresh squeezed orange juice", BigDecimal.valueOf(3),tablespoonOptional.get());
        tacoIngredientSet.add(orangeJuice);

        Ingredient oliveOil = new Ingredient();
        oliveOil.newIngredient("olive oil", BigDecimal.valueOf(2),tablespoonOptional.get());
        tacoIngredientSet.add(oliveOil);

        Ingredient chicken = new Ingredient();
        chicken.newIngredient("skinless, boneless chicken thighs", BigDecimal.valueOf(1.25),poundOptional.get());
        tacoIngredientSet.add(chicken);

        Ingredient tortilla = new Ingredient();
        tortilla.newIngredient("small corn tortillas", BigDecimal.valueOf(8),eachOptional.get());
        tacoIngredientSet.add(tortilla);

        Ingredient arugula = new Ingredient();
        arugula.newIngredient("packed baby arugula", BigDecimal.valueOf(3),cupOptional.get());
        tacoIngredientSet.add(arugula);

        Ingredient radish = new Ingredient();
        radish.newIngredient("radishes, thinly sliced", BigDecimal.valueOf(4),eachOptional.get());
        tacoIngredientSet.add(radish);

        Ingredient avocado = new Ingredient();
        avocado.newIngredient("medium ripe avocados, sliced", BigDecimal.valueOf(2),eachOptional.get());
        tacoIngredientSet.add(avocado);

        Ingredient cherryTomato = new Ingredient();
        cherryTomato.newIngredient("cherry tomatoes, halved", BigDecimal.valueOf(0.5),pintOptional.get());
        tacoIngredientSet.add(cherryTomato);

        Ingredient onion = new Ingredient();
        onion.newIngredient("red onion, thinly sliced", BigDecimal.valueOf(0.25),eachOptional.get());
        tacoIngredientSet.add(onion);

        Ingredient tacoCilantro = new Ingredient();
        tacoCilantro.newIngredient("roughly chopped cilantro", BigDecimal.ONE, eachOptional.get());
        tacoIngredientSet.add(tacoCilantro);

        Ingredient SourCream = new Ingredient();
        SourCream.newIngredient("sour cream", BigDecimal.valueOf(0.5), cupOptional.get());
        tacoIngredientSet.add(SourCream);

        Ingredient tacoLime = new Ingredient();
        tacoLime.newIngredient("lime, cut into wedges", BigDecimal.ONE, eachOptional.get());
        tacoIngredientSet.add(tacoLime);

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

        Set<Category>tacoCategories = new HashSet<>();
        if (texMex.isPresent()) {
            tacoCategories.add(texMex.get());}
        if (mexican.isPresent()) {
            tacoCategories.add(mexican.get());}
        tacos.setCategories(tacoCategories);
        recipeRepository.save(tacos);

        System.out.println(String.format("recipe tacos saved to recipe repository. There are %s recipes in the repository.",recipeRepository.count()));

        System.out.println("\n\nBootstrapData completed.");
    }
}
