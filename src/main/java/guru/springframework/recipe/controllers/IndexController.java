package guru.springframework.recipe.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model theModel) {
		
		Set<Recipe> recipes = recipeService.findAll();
		theModel.addAttribute("recipes", recipes);
		
		return "index";
	}

}
