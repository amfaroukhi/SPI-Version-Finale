package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the EVALUATION database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),
		@NamedQuery(name = "Evaluation.findByPromotion", query = "SELECT e FROM Evaluation e WHERE e.codeFormation = :codeFormation AND e.anneeUniversitaire = :anneeUniversitaire") })
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen3")
	@SequenceGenerator(name = "my_seq_gen3", sequenceName = "EVE_SEQ")
	@Column(name = "ID_EVALUATION")
	private long idEvaluation;

	@Column(name = "ANNEE_UNIVERSITAIRE")
	private String anneeUniversitaire;

	@Column(name = "CODE_EC")
	private String codeEc;

	@Column(name = "CODE_FORMATION")
	private String codeFormation;

	@Column(name = "CODE_UE")
	private String codeUe;

	@Temporal(TemporalType.DATE)
	@Column(name = "DEBUT_REPONSE")
	private Date debutReponse;

	private String designation;

	private String etat;

	@Temporal(TemporalType.DATE)
	@Column(name = "FIN_REPONSE")
	private Date finReponse;

	@Column(name = "NO_ENSEIGNANT")
	private BigDecimal noEnseignant;

	@Column(name = "NO_EVALUATION")
	private BigDecimal noEvaluation;

	private String periode;

	public Evaluation() {
	}

	public Evaluation(long idEvaluation) {
		super();
		this.idEvaluation = idEvaluation;
	}

	public long getIdEvaluation() {
		return this.idEvaluation;
	}

	public void setIdEvaluation(long idEvaluation) {
		this.idEvaluation = idEvaluation;
	}

	public String getAnneeUniversitaire() {
		return this.anneeUniversitaire;
	}

	public void setAnneeUniversitaire(String anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}

	public String getCodeEc() {
		return this.codeEc;
	}

	public void setCodeEc(String codeEc) {
		this.codeEc = codeEc;
	}

	public String getCodeFormation() {
		return this.codeFormation;
	}

	public void setCodeFormation(String codeFormation) {
		this.codeFormation = codeFormation;
	}

	public String getCodeUe() {
		return this.codeUe;
	}

	public void setCodeUe(String codeUe) {
		this.codeUe = codeUe;
	}

	public Date getDebutReponse() {
		return this.debutReponse;
	}

	public void setDebutReponse(Date debutReponse) {
		this.debutReponse = debutReponse;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getFinReponse() {
		return this.finReponse;
	}

	public void setFinReponse(Date finReponse) {
		this.finReponse = finReponse;
	}

	public BigDecimal getNoEnseignant() {
		return this.noEnseignant;
	}

	public void setNoEnseignant(BigDecimal noEnseignant) {
		this.noEnseignant = noEnseignant;
	}

	public BigDecimal getNoEvaluation() {
		return this.noEvaluation;
	}

	public void setNoEvaluation(BigDecimal noEvaluation) {
		this.noEvaluation = noEvaluation;
	}

	public String getPeriode() {
		return this.periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

}