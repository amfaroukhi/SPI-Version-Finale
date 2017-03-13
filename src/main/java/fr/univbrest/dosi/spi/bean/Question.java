package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;


/**
 * The persistent class for the QUESTION database table.
 * 
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen5")
	@SequenceGenerator(name="my_seq_gen5", sequenceName="QUE_SEQ")
	@Column(name="ID_QUESTION")
	private long idQuestion;

	private String intitule;

	@Column(name="NO_ENSEIGNANT")
	private java.math.BigDecimal noEnseignant;

	@Column(name="TYPE")
	private String type;

	//uni-directional many-to-one association to Qualificatif
	@RestResource(exported = false)
	@ManyToOne
	@JoinColumn(name="ID_QUALIFICATIF")
	private Qualificatif qualificatif;

	public Question() {
	}

	public long getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public java.math.BigDecimal getNoEnseignant() {
		return this.noEnseignant;
	}

	public void setNoEnseignant(java.math.BigDecimal noEnseignant) {
		this.noEnseignant = noEnseignant;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Qualificatif getQualificatif() {
		return this.qualificatif;
	}

	public void setQualificatif(Qualificatif qualificatif) {
		this.qualificatif = qualificatif;
	}

}