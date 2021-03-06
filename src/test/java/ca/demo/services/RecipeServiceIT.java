package ca.demo.services;


import ca.demo.commands.RecipeCommand;
import ca.demo.converters.RecipeCommandToRecipe;
import ca.demo.converters.RecipeToRecipeCommand;
import ca.demo.domain.Recipe;
import ca.demo.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        Assert.assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        Assert.assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        Assert.assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        Assert.assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
