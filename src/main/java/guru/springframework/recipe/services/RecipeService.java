package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> findAll();
	
	public Recipe findById(Long id);

	public RecipeCommand findCommandById(Long id);
	
	public Recipe save(Recipe recipe);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand command);

	public void deleteById(Long id);

} 
