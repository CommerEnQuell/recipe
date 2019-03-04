package guru.springframework.recipe.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl extends AbstractServiceImpl<Category, Long> implements CategoryService {
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super(categoryRepository);
	}
	
	@Override
	public Category findById(Long id) {
		Optional<Category> o = repository.findById(id);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Category #" + id + " not found!");
		}
		return o.get();
	}
	
	@Override
	public Set<Category> findAll() {
		return super.findAll();
	}

	@Override
	public Category findByDescription(String description) {
		Optional<Category> o = ((CategoryRepository) repository).findByDescription(description);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Category not found: " + description);
		}
		
		Category retval = o.get();
		return retval;
	}
	
	@Override
	public Category save(Category category) {
		Category retval = super.save(category);
		return retval;
	}

}
