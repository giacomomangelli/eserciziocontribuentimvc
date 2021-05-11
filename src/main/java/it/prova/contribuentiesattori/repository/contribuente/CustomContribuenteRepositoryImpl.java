package it.prova.contribuentiesattori.repository.contribuente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.contribuentiesattori.model.Contribuente;

public class CustomContribuenteRepositoryImpl implements CustomContribuenteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contribuente> findByExample(Contribuente contribuenteExample) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Contribuente c where c.id = c.id ");

		if (StringUtils.isNotEmpty(contribuenteExample.getNome())) {
			whereClauses.add(" c.nome  like :nome ");
			paramaterMap.put("nome", "%" + contribuenteExample.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(contribuenteExample.getCognome())) {
			whereClauses.add(" c.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + contribuenteExample.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(contribuenteExample.getCodiceFiscale())) {
			whereClauses.add(" c.codice_fiscale like :codiceFiscale ");
			paramaterMap.put("codiceFiscale", "%" + contribuenteExample.getCodiceFiscale() + "%");
		}
		if (contribuenteExample.getDataDiNascita() != null) {
			whereClauses.add("c.data_di_nascita >= :dataDiNascita ");
			paramaterMap.put("dataDiNascita", contribuenteExample.getDataDiNascita());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Contribuente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Contribuente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
