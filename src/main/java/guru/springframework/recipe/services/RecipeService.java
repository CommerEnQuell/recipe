package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> findAll();
	
	public Recipe save(Recipe recipe);
	
	public Recipe findById(Long id);
} 
