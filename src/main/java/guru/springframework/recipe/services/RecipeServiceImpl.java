package guru.springframework.recipe.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl extends AbstractServiceImpl<Recipe, Long> implements RecipeService {

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super(recipeRepository);
	}
	
	@Override
	public Set<Recipe> findAll() {
		return super.findAll();
	}
	
	@Override
	public Recipe save(Recipe recipe) {
		Recipe savedRecipe =  super.save(recipe);
		return savedRecipe;
	}
}
