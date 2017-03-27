package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;


/**
 * The persistent class for the RUBRIQUE_EVALUATION database table.
 * 
 */
@Entity
@Table(name="RUBRIQUE_EVALUATION")
@NamedQuery(name="RubriqueEvaluation.findAll", query="SELECT r FROM RubriqueEvaluation r")
public class RubriqueEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	//@RestResource(exported = false)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen7")
	@SequenceGenerator(name="my_seq_gen7", sequenceName="REV_SEQ")
	@Column(name="ID_RUBRIQUE_EVALUATION")
	private long idRubriqueEvaluation;

	private String designation;

	private BigDecimal ordre;

	//uni-directional many-to-one association to Evaluation
	@RestResource(exported = false)
	@ManyToOne
	@JoinColumn(name="ID_EVALUATION")
	private Evaluation evaluation;

	//uni-directional many-to-one association to Rubrique
	@RestResource(exported = false)
	@ManyToOne
	@JoinColumn(name="ID_RUBRIQUE")
	private Rubrique rubrique;

	public RubriqueEvaluation() {
	}

	public long getIdRubriqueEvaluation() {
		return this.idRubriqueEvaluation;
	}

	public void setIdRubriqueEvaluation(long idRubriqueEvaluation) {
		this.idRubriqueEvaluation = idRubriqueEvaluation;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public BigDecimal getOrdre() {
		return this.ordre;
	}

	public void setOrdre(BigDecimal ordre) {
		this.ordre = ordre;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Rubrique getRubrique() {
		return this.rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

}