package it.prova.contribuentiesattori.service.contribuente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.contribuentiesattori.model.Contribuente;
import it.prova.contribuentiesattori.repository.contribuente.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {

	@Autowired
	private ContribuenteRepository repository;
	
	@Transactional(readOnly = true)
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Transactional
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);
	}

	@Transactional(readOnly = true)
	public List<Contribuente> findByExample(Contribuente contribuenteExample) {
		return repository.findByExample(contribuenteExample);
	}

	@Transactional(readOnly = true)
	public List<Contribuente> cercaByCognomeENomeLike(String part) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(part, part);
	}
	
}
