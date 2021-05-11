package it.prova.contribuentiesattori.model;

public enum StatoCartellaEsattoriale {
	
	CREATA,
	IN_VERIFICA,
	CONCLUSA,
	IN_CONTENZIOSO,
	INVALIDATA;
	
	public static StatoCartellaEsattoriale getStatoByString(String statoString) {
		StatoCartellaEsattoriale[] stati = StatoCartellaEsattoriale.values();
		for(StatoCartellaEsattoriale statoItem : stati) {
			if(statoString.equalsIgnoreCase(statoItem.name()))
				return statoItem;
		}
		return null;
	}

}
