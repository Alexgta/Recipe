package ca.demo.repositories;

import ca.demo.domain.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {

}
