package it.prova.contribuentiesattori.service.cartellaesattoriale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;
import it.prova.contribuentiesattori.model.StatoCartellaEsattoriale;
import it.prova.contribuentiesattori.repository.cartellaesattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {

	@Autowired
	private CartellaEsattorialeRepository repository;
	
	@Transactional(readOnly = true)
	public List<CartellaEsattoriale> listAllElements() {
		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

	@Transactional
	public void aggiorna(CartellaEsattoriale cartellaEsattattorialeInstance) {
		repository.save(cartellaEsattattorialeInstance);
	}

	@Transactional
	public void inserisciNuovo(CartellaEsattoriale cartellaEsattattorialeInstance) {
		repository.save(cartellaEsattattorialeInstance);
	}

	@Transactional
	public void rimuovi(CartellaEsattoriale cartellaEsattattorialeInstance) {
		repository.delete(cartellaEsattattorialeInstance);
	}

	@Transactional(readOnly = true)
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale cartellaEsattattorialeExample) {
		return repository.findByExample(cartellaEsattattorialeExample);
	}

	@Transactional
	public void disabilita(CartellaEsattoriale cartellaEsattorialeInstance) {
		cartellaEsattorialeInstance.setStatoCartella(StatoCartellaEsattoriale.INVALIDATA);
		repository.save(cartellaEsattorialeInstance);
	}

}
