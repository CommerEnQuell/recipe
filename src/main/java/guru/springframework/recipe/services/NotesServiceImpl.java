package guru.springframework.recipe.services;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.repositories.NotesRepository;

@Service
public class NotesServiceImpl extends AbstractServiceImpl<Notes, Long> implements NotesService {
	
	public NotesServiceImpl(NotesRepository notesRepository) {
		super(notesRepository);
	}

	@Override
	public Notes findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Notes save(Notes notes) {
		Notes savedNotes = repository.save(notes);
		return savedNotes;
	}

}
