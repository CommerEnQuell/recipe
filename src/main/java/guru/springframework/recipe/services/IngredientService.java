package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;

public interface IngredientService {
	
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
	public IngredientCommand saveIngredientCommand(IngredientCommand command);
	public void deleteById(Long recipeId, Long idToDelete);
}
