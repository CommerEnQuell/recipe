package guru.springframework.recipe.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.recipe.domain.Category;

public interface CategoryService {

		public Optional<Category> findById(Long id);
		public Category findByDescription(String description);
		
		public Set<Category> findAll();
		
		public Category save(Category category);

}
