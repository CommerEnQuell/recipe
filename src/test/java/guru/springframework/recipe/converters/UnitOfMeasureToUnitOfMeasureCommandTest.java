package guru.springframework.recipe.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;

class UnitOfMeasureToUnitOfMeasureCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);

	private UnitOfMeasureToUnitOfMeasureCommand converter;
	
	

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testNotNullObject() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}
	
	@Test
	public void convert() throws Exception {
		// given
		UnitOfMeasure entity = new UnitOfMeasure();
		entity.setId(LONG_VALUE);
		entity.setDescription(DESCRIPTION);
		
		// when
		UnitOfMeasureCommand command = converter.convert(entity);
		
		// then
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
	}

}
