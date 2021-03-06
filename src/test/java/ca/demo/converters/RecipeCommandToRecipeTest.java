package ca.demo.converters;

import ca.demo.commands.CategoryCommand;
import ca.demo.commands.IngredientCommand;
import ca.demo.commands.NotesCommand;
import ca.demo.commands.RecipeCommand;
import ca.demo.domain.Difficulty;
import ca.demo.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    /*@Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }*/

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //when
        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        Assert.assertEquals(RECIPE_ID, recipe.getId());
        Assert.assertEquals(COOK_TIME, recipe.getCookTime());
        Assert.assertEquals(PREP_TIME, recipe.getPrepTime());
        Assert.assertEquals(DESCRIPTION, recipe.getDescription());
        Assert.assertEquals(DIFFICULTY, recipe.getDifficulty());
        Assert.assertEquals(DIRECTIONS, recipe.getDirections());
        Assert.assertEquals(SERVINGS, recipe.getServings());
        Assert.assertEquals(SOURCE, recipe.getSource());
        Assert.assertEquals(URL, recipe.getUrl());
        Assert.assertEquals(NOTES_ID, recipe.getNotes().getId());
        Assert.assertEquals(2, recipe.getCategories().size());
        Assert.assertEquals(2, recipe.getIngredients().size());
    }

}