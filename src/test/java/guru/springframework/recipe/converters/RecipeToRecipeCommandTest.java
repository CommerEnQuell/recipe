package guru.springframework.recipe.converters;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Identifiable;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;

class RecipeToRecipeCommandTest {
	public static final Long RECIPE_ID = new Long(1L);
	public static final Long UOM_ID_1  = new Long(11L);
	public static final Long UOM_ID_2  = new Long(12L);
	public static final Long CAT_ID    = new Long(21L);
	public static final Long ING_ID_1  = new Long(31L);
	public static final Long ING_ID_2  = new Long(32L);
	public static final Long ING_ID_3  = new Long(33L);
	public static final Long NOTES_ID  = new Long(41L);

	
	public static final Integer TIME_MIN = new Integer(10);
	public static final Integer SERVINGS = new Integer(4);
	
	public static final String LOREM_IPSUM = "Lorem ipsum etc.";
	public static final String HOCUS_POCUS = "Hocus Pocus etc.";
	public static final String HOTUM_FACTOTUM = "Hotum Factotum etc.";
	public static final String EXAMPLE_URL = "http://www.example.com";
	public static final String UOM_1 = "Freightload";
	public static final String UOM_2 = "Pieces";
	public static final String CAT   = "Convertable";
	public static final String ING_1 = "Fried Air";
	public static final String ING_2 = "Hot Potatoes";
	public static final String ING_3 = "Egg Yolk";
	
	
	RecipeToRecipeCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(
						new CategoryToCategoryCommand(),
						new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
						new NotesToNotesCommand());
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Recipe()));
	}

	@Test
	void convert() {
		// given
		Recipe source = new Recipe();
		source.setId(RECIPE_ID);
		source.setDescription(LOREM_IPSUM);
		source.setDifficulty(Difficulty.MODERATE);
		source.setCookTime(TIME_MIN);
		source.setPrepTime(TIME_MIN);
		source.setServings(SERVINGS);
		source.setDirections(HOCUS_POCUS);
		source.setSource(HOTUM_FACTOTUM);
		source.setUrl(EXAMPLE_URL);
		
		Notes notes = new Notes();
		notes.setId(NOTES_ID);
		notes.setRecipeNotes(LOREM_IPSUM);
		
		source.setNotes(notes);
		
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(UOM_ID_1);
		uom1.setDescription(UOM_1);
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(UOM_ID_2);
		uom2.setDescription(UOM_2);
		
		Category cat = new Category();
		cat.setId(CAT_ID);
		cat.setDescription(CAT);
		
		Set<Category> categories = new HashSet<>();
		categories.add(cat);
		source.setCategories(categories);
		
		Ingredient ing1 = new Ingredient();
		ing1.setId(ING_ID_1);
		ing1.setAmount(new BigDecimal(1));
		ing1.setUom(uom1);
		ing1.setDescription(ING_1);

		Ingredient ing2 = new Ingredient();
		ing2.setId(ING_ID_2);
		ing2.setAmount(new BigDecimal(8));
		ing2.setUom(uom2);
		ing2.setDescription(ING_2);

		Ingredient ing3 = new Ingredient();
		ing3.setId(ING_ID_3);
		ing3.setAmount(new BigDecimal(4));
		ing3.setUom(uom2);
		ing3.setDescription(ING_3);

		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(ing1);
		ingredients.add(ing2);
		ingredients.add(ing3);
		source.setIngredients(ingredients);

		// when
		RecipeCommand dest = converter.convert(source);
		
		Map<Long, Identifiable> catMap = toMap(dest.getCategories());
		Map<Long, Identifiable> ingMap = toMap(dest.getIngredients());
		
		
		// then
		assertNotNull(dest);
		assertNotNull(dest.getNotes());
		assertNotNull(dest.getCategories());
		assertNotNull(dest.getIngredients());
		assertEquals(source.getCategories().size(), dest.getCategories().size());
		assertEquals(source.getIngredients().size(), dest.getIngredients().size());
		
		assertEquals(RECIPE_ID, dest.getId());
		assertEquals(LOREM_IPSUM, dest.getDescription());
		assertEquals(Difficulty.MODERATE, dest.getDifficulty());
		assertEquals(TIME_MIN, dest.getPrepTime());
		assertEquals(TIME_MIN, dest.getCookTime());
		assertEquals(SERVINGS, dest.getServings());
		assertEquals(HOCUS_POCUS, dest.getDirections());
		assertEquals(HOTUM_FACTOTUM, dest.getSource());
		assertEquals(EXAMPLE_URL, dest.getUrl());
		
		NotesCommand destNotes = dest.getNotes();
		assertEquals(NOTES_ID, destNotes.getId());
		assertEquals(LOREM_IPSUM, destNotes.getRecipeNotes());
		
		for (Category s : source.getCategories()) {
			assumeTrue(catMap.containsKey(s.getId()));
			CategoryCommand d = (CategoryCommand) catMap.get(s.getId());
			assertEquals(s.getId(), d.getId());
			assertEquals(s.getDescription(), d.getDescription());
		}
		
		for (Ingredient s : source.getIngredients()) {
			assumeTrue(ingMap.containsKey(s.getId()));
			IngredientCommand d = (IngredientCommand) ingMap.get(s.getId());
			assertNotNull(d.getUom());
			assertEquals(s.getId(), d.getId());
			assertEquals(s.getAmount(), d.getAmount());
			assertEquals(s.getDescription(), d.getDescription());
			assertEquals(s.getUom().getId(), d.getUom().getId());
			assertEquals(s.getUom().getDescription(), d.getUom().getDescription());
		}
	}

	private Map<Long, Identifiable> toMap(Set<? extends Identifiable> identifiables) {
		Map<Long, Identifiable> retval = new HashMap<>();
		identifiables.forEach(record -> retval.put(record.getId(), record));
		return retval;
		
	}
}
