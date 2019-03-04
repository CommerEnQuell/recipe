package guru.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.services.RecipeService;

@Controller
public class RecipeController {
	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model theModel) {
		theModel.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}

}
