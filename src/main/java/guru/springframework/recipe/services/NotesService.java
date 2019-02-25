package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Notes;

public interface NotesService {

	public Notes findById(Long id);
	
	public Notes save(Notes notes);
}
