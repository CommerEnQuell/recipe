package guru.springframework.recipe.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.domain.Category;

class CategoryCommandToCategoryTest {
	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);
	
	CategoryCommandToCategory converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void convert() throws Exception {
		// given
		CategoryCommand command = new CategoryCommand();
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);
		
		// when
		Category entity = converter.convert(command);
		
		// then
		assertNotNull(entity);
		assertEquals(LONG_VALUE, entity.getId());
		assertEquals(DESCRIPTION, entity.getDescription());
	}

}
