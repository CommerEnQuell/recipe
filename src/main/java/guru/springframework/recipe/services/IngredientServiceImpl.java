package guru.springframework.recipe.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl extends AbstractServiceImpl<Ingredient, Long> implements IngredientService {

	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		super(ingredientRepository);
	}

	@Override
	public Ingredient findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Set<Ingredient> findAll() {
		return super.findAll();
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		return super.save(ingredient);
	}

}
