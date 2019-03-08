package guru.springframework.recipe.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.domain.Notes;

class NotesToNotesCommandTest {

	private static final Long LONG_VALUE = new Long(1L);
	private static final String LOREM_IPSUM = "Lorem ipsum etc.";

	NotesToNotesCommand converter;
	
	@BeforeEach
	void setUp() throws Exception {
		this.converter = new NotesToNotesCommand();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Notes()));
	}
	
	@Test
	public void convert() throws Exception {
		// given
		Notes source = new Notes();
		source.setId(LONG_VALUE);
		source.setRecipeNotes(LOREM_IPSUM);
		
		// when
		NotesCommand dest = converter.convert(source);
		
		// then
		assertNotNull(dest);
		assertEquals(LONG_VALUE, dest.getId());
		assertEquals(LOREM_IPSUM, dest.getRecipeNotes());
	}
}
