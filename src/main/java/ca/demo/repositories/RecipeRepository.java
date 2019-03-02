package ca.demo.repositories;

import ca.demo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
