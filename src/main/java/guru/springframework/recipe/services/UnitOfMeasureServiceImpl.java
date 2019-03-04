package guru.springframework.recipe.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl extends AbstractServiceImpl<UnitOfMeasure, Long> implements UnitOfMeasureService {
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		super(unitOfMeasureRepository);
	}
	
	@Override
	public UnitOfMeasure findById(Long id) {
		Optional<UnitOfMeasure> o = repository.findById(id);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Unit of Measure #" + id + " not found!");
		}
		return o.get();
	}
	
	@Override
	public Set<UnitOfMeasure> findAll() {
		return super.findAll();
	}

	@Override
	public UnitOfMeasure findByDescription(String description) {
		UnitOfMeasureRepository uomRepository = (UnitOfMeasureRepository) repository;
		Optional<UnitOfMeasure> o = uomRepository.findByDescription(description);
		if (o == null || !o.isPresent()) {
			throw new RuntimeException("Unit of measure not found: " + description);
		}
		
		UnitOfMeasure retval = o.get();
		return retval;
	}
	
	@Override
	public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
		UnitOfMeasure savedUOM = super.save(unitOfMeasure);
		return savedUOM;
	}
}
