package guru.springframework.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RecipeServiceImpl extends AbstractServiceImpl<Recipe, Long> implements RecipeService {

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super(recipeRepository);
	}
	
	@Override
	public Set<Recipe> findAll() {
		return super.findAll();
	}

	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");
		
		Set<Recipe> recipeSet = new HashSet<>();
		
		return super.findAll();
		
	}
	
	@Override
	public Recipe findById(Long id) {
		log.debug("Finding recipe with id #" + id);
		
		Optional<Recipe> o =  repository.findById(id);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Recipe #" + id + " not found!");
		}
		
		return o.get();
	}
	
	@Override
	public Recipe save(Recipe recipe) {
		Recipe savedRecipe =  super.save(recipe);
		return savedRecipe;
	}
}
