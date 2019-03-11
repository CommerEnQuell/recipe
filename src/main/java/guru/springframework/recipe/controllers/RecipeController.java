package guru.springframework.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.exceptions.NotFoundException;
import guru.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {
	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model theModel) {
		theModel.addAttribute("recipe", recipeService.findById(new Long(id)).get());
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception x) {
		log.error("Handling not found exception");
		log.error(x.getMessage());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", x);
		return modelAndView;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormat(Exception x) {
		log.error("Handling Number Format exception");
		log.error(x.getMessage());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("400error");
		modelAndView.addObject("exception", x);
		return modelAndView;
	}

	@GetMapping("recipe/{id}/delete")
	public String delete(@PathVariable String id) {
		log.debug("Deleting id: " + id);
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}

}
