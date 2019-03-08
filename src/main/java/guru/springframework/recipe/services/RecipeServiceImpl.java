package guru.springframework.recipe.services;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.converters.RecipeCommandToRecipe;
import guru.springframework.recipe.converters.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RecipeServiceImpl extends AbstractServiceImpl<Recipe, Long> implements RecipeService {
	
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		super(recipeRepository);
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}
	
	@Override
	public Set<Recipe> findAll() {
		return super.findAll();
	}

	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service");
		
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
	@Transactional
	public RecipeCommand findCommandById(Long id) {
		Recipe recipe = findById(id);
		
		RecipeCommand retval = recipeToRecipeCommand.convert(recipe);
		return retval;
	}
	
	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
		
		Recipe savedRecipe = save(detachedRecipe);
		log.debug("Saved recipeId: " + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
