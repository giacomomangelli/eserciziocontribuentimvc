package it.prova.contribuentiesattori.repository.cartellaesattoriale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.contribuentiesattori.model.CartellaEsattoriale;

public class CustomCartellaEsattorialeRepositoryImpl implements CustomCartellaEsattorialeRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale cartellaExample) {
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from CartellaEsattoriale c left join fetch c.contribuente co where c.id = c.id ");

		if (StringUtils.isNotEmpty(cartellaExample.getDescrizione())) {
			whereClauses.add(" c.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + cartellaExample.getDescrizione() + "%");
		}
		if (cartellaExample.getImporto() != null && cartellaExample.getImporto()>0) {
			whereClauses.add(" c.importo >= :importo ");
			paramaterMap.put("importo", "%" + cartellaExample.getImporto() + "%");
		}
		if (cartellaExample.getContribuente()!=null && cartellaExample.getContribuente().getId()!=null) {
			whereClauses.add(" co = :contribuente ");
			paramaterMap.put("contribuente", cartellaExample.getContribuente());
		}
		if (cartellaExample.getStatoCartella() != null) {
			whereClauses.add(" c.statoCartella like :stato ");
			paramaterMap.put("stato", cartellaExample.getStatoCartella());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<CartellaEsattoriale> typedQuery = entityManager.createQuery(queryBuilder.toString(), CartellaEsattoriale.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}


}
