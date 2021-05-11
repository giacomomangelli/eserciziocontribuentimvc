package it.prova.contribuentiesattori.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cartella_esattoriale")
public class CartellaEsattoriale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	@Column(name = "descrizione")
	private String descrizione;

	@NotNull(message = "{importo.notnull}")
	@DecimalMin("1.00")
	@Column(name = "importo")
	private Double importo;

	@NotNull(message = "{statoCartella.notblank}")
	@Enumerated(EnumType.STRING)
	@Column(name = "stato_cartella")
	private StatoCartellaEsattoriale statoCartella;

	@NotNull(message = "contribuente.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_contribuente")
	private Contribuente contribuente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public StatoCartellaEsattoriale getStatoCartella() {
		return statoCartella;
	}

	public void setStatoCartella(StatoCartellaEsattoriale statoCartella) {
		this.statoCartella = statoCartella;
	}

	public Contribuente getContribuente() {
		return contribuente;
	}

	public void setContribuente(Contribuente contribuente) {
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale(Long id, @NotBlank(message = "{descrizione.notblank }") String descrizione,
			@NotBlank(message = "{importo.notblank}") @DecimalMin("1.00") Double importo,
			@NotBlank(message = "{statoCartella.notblank }") StatoCartellaEsattoriale statoCartella,
			@NotNull(message = "contribuente.notnull}") Contribuente contribuente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale(Long id, @NotBlank(message = "{descrizione.notblank }") String descrizione,
			@NotBlank(message = "{importo.notblank}") @DecimalMin("1.00") Double importo,
			@NotBlank(message = "{statoCartella.notblank }") StatoCartellaEsattoriale statoCartella) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
	}

	public CartellaEsattoriale(@NotBlank(message = "{descrizione.notblank }") String descrizione,
			@NotBlank(message = "{importo.notblank}") @DecimalMin("1.00") Double importo,
			@NotBlank(message = "{statoCartella.notblank }") StatoCartellaEsattoriale statoCartella) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.statoCartella = statoCartella;
	}

	public CartellaEsattoriale(@NotBlank(message = "{descrizione.notblank }") String descrizione,
			@NotBlank(message = "{importo.notblank}") @DecimalMin("1.00") Double importo) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
	}

	public CartellaEsattoriale(@NotNull(message = "{statoCartella.notblank}") StatoCartellaEsattoriale statoCartella) {
		super();
		this.statoCartella = statoCartella;
	}

	public CartellaEsattoriale() {

	}

}
