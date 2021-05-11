package it.prova.contribuentiesattori.service.cartellaesattoriale;

import java.util.List;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {
	
	public List<CartellaEsattoriale> listAllElements();

	public CartellaEsattoriale caricaSingoloElemento(Long id);
	
	public CartellaEsattoriale caricaSingoloElementoEager(Long id);

	public void aggiorna(CartellaEsattoriale cartellaEsattattorialeInstance);

	public void inserisciNuovo(CartellaEsattoriale cartellaEsattattorialeInstance);

	public void rimuovi(CartellaEsattoriale cartellaEsattattorialeInstance);

	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale cartellaEsattattorialeExample);

	public void disabilita(CartellaEsattoriale cartellaEsattorialeInstance);
}
