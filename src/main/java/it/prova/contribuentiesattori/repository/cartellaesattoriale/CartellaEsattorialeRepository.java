package it.prova.contribuentiesattori.repository.cartellaesattoriale;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository {

	@Query("select c from CartellaEsattoriale c left join fetch c.contribuente where c.id = ?1")
	Optional<CartellaEsattoriale> findByIdEager(Long id);
	
}
