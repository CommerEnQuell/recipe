package guru.springframework.recipe.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.converters.NotesCommandToNotes;
import guru.springframework.recipe.converters.NotesToNotesCommand;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.repositories.NotesRepository;

@Service
public class NotesServiceImpl extends AbstractServiceImpl<Notes, Long> implements NotesService {

	private final NotesCommandToNotes notesCommandToNotes;
	private final NotesToNotesCommand notesToNotesCommand;
	
	public NotesServiceImpl(NotesRepository notesRepository, NotesCommandToNotes notesCommandToNotes, NotesToNotesCommand notesToNotesCommand) {
		super(notesRepository);
		this.notesCommandToNotes = notesCommandToNotes;
		this.notesToNotesCommand = notesToNotesCommand;
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
	
	@Override
	@Transactional
	public NotesCommand saveNotesCommand(NotesCommand command) {
		Notes detachedNotes = notesCommandToNotes.convert(command);
		
		Notes savedNotes = super.save(detachedNotes);
		return notesToNotesCommand.convert(savedNotes);
		
	}

}
