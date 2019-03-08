package guru.springframework.recipe.services;

import java.util.Set;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

	public UnitOfMeasure findById(Long id);
	public UnitOfMeasure findByDescription(String description);
	
	public Set<UnitOfMeasure> findAll();
	
	public UnitOfMeasure save(UnitOfMeasure unitOfMeasure);
	
	Set<UnitOfMeasureCommand> listAllUoms();

}
