package guru.springframework.recipes.repository;

import guru.springframework.recipes.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategory(String cat);
}
