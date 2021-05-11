package it.prova.contribuentiesattori.service.contribuente;

import java.util.List;

import it.prova.contribuentiesattori.model.Contribuente;

public interface ContribuenteService {
	
	public List<Contribuente> listAllElements();

	public Contribuente caricaSingoloElemento(Long id);
	
	public Contribuente caricaSingoloElementoEager(Long id);

	public void aggiorna(Contribuente contribuenteInstance);

	public void inserisciNuovo(Contribuente contribuenteInstance);

	public void rimuovi(Contribuente contribuenteInstance);

	public List<Contribuente> findByExample(Contribuente contribuenteExample);

	public List<Contribuente> cercaByCognomeENomeLike(String part);
}
