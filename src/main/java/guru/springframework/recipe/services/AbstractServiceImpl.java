package guru.springframework.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractServiceImpl<T, ID> {
	
	protected final CrudRepository<T, ID> repository;
	
	protected AbstractServiceImpl(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	public T findById(ID id) {
		T retval = null;
		Optional<T> o = repository.findById(id);
		if (o != null && o.isPresent()) {
			retval = o.get();
		}
		return retval;
	}
	
	public Set<T> findAll() {
		Set<T> retval = new HashSet<>();
		repository.findAll().forEach(t -> retval.add(t));
		return retval;
	}

	public T save(T entity) {
		return repository.save(entity);
	}
}
