package guru.springframework.recipe.commands;

import guru.springframework.recipe.domain.Identifiable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand implements Identifiable {
	private Long id;
	private String description;
}
