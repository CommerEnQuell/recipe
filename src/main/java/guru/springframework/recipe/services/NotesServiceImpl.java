package guru.springframework.recipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.NotesRepository;

@Service
public class NotesServiceImpl extends AbstractServiceImpl<Notes, Long> implements NotesService {
	
	public NotesServiceImpl(NotesRepository notesRepository) {
		super(notesRepository);
	}

	@Override
	public Notes findById(Long id) {
		Optional<Notes> o = repository.findById(id);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Notes #" + id + " not found!");
		}
		return o.get();
	}

	@Override
	public Notes save(Notes notes) {
		Notes savedNotes = repository.save(notes);
		return savedNotes;
	}

}
