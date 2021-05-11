package it.prova.contribuentiesattori.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "contribuente")
public class Contribuente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	@Column(name = "cognome")
	private String cognome;
	
	@NotNull(message = "{dataDiNascita.notnull}")
	@Column(name = "data_di_nascita")
	private Date dataDiNascita;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {min} caratteri")
	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@NotBlank(message = "{indirizzo.notblank}")
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private Set<CartellaEsattoriale> cartelleEsattoriali = new HashSet<>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<CartellaEsattoriale> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattoriale> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Contribuente(Long id, @NotBlank(message = "{nome.notblank }") String nome,
			@NotBlank(message = "{cognome.notblank }") String cognome,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita,
			@NotBlank(message = "{codiceFiscale.notblak}") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo,
			Set<CartellaEsattoriale> cartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Contribuente(Long id, @NotBlank(message = "{nome.notblank }") String nome,
			@NotBlank(message = "{cognome.notblank }") String cognome,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita,
			@NotBlank(message = "{codiceFiscale.notblak}") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public Contribuente(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita,
			@NotBlank(message = "{codiceFiscale.notblak}") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}
	
	public Contribuente() {
		
	}

}
