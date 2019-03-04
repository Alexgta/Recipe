package ca.demo.services;

import ca.demo.domain.Recipe;
import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();
}
