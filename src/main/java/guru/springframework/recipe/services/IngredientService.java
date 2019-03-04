package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.domain.Ingredient;

public interface IngredientService {

	public Ingredient findById(Long id);
	
	public Set<Ingredient> findAll();
	
	public Ingredient save(Ingredient ingredient);
}
