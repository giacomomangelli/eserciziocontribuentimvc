package it.prova.contribuentiesattori.repository.contribuente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.contribuentiesattori.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository {

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome, String nome);
	
	@Query("select c from Contribuente c left join fetch c.cartelleEsattoriali where c.id = ?1")
	Optional<Contribuente> findByIdEager(Long id);

}
