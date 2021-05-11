package it.prova.contribuentiesattori.repository.contribuente;

import java.util.List;

import it.prova.contribuentiesattori.model.Contribuente;

public interface CustomContribuenteRepository {
	
	List<Contribuente> findByExample(Contribuente contribuenteExample);

}
