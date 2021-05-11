package it.prova.contribuentiesattori.repository.cartellaesattoriale;

import java.util.List;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;

public interface CustomCartellaEsattorialeRepository {
	
	List<CartellaEsattoriale> findByExample(CartellaEsattoriale cartellaExample);

}
