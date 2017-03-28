package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;


/**
 * The persistent class for the QUESTION_EVALUATION database table.
 * 
 */
@Entity
@Table(name="QUESTION_EVALUATION")
//@NamedQuery(name="QuestionEvaluation.findAll", query="SELECT q FROM QuestionEvaluation q")
@NamedQueries({
    @NamedQuery(name="QuestionEvaluation.findAll", query="SELECT q FROM QuestionEvaluation q"),
    @NamedQuery(name = "QuestionEvaluation.findByRubriqueEvaluation", query = "SELECT q FROM QuestionEvaluation q WHERE q.rubriqueEvaluation = :rubriqueEvaluation")})
public class QuestionEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen20")
	@SequenceGenerator(name="my_seq_gen20", sequenceName="QEV_SEQ")
	@Column(name="ID_QUESTION_EVALUATION")
	private long idQuestionEvaluation;

	private String intitule;

	private BigDecimal ordre;

	//uni-directional many-to-one association to Qualificatif
	@ManyToOne
	@RestResource(exported=false)
	@JoinColumn(name="ID_QUALIFICATIF")
	private Qualificatif qualificatif;

	//uni-directional many-to-one association to Question
	@ManyToOne
	@RestResource(exported=false)
	@JoinColumn(name="ID_QUESTION")
	private Question question;

	//uni-directional many-to-one association to RubriqueEvaluation
	@ManyToOne
	@RestResource(exported=false)
	@JoinColumn(name="ID_RUBRIQUE_EVALUATION")
	private RubriqueEvaluation rubriqueEvaluation;

	public QuestionEvaluation() {
	}

	public QuestionEvaluation(long idQuestionEvaluation) {
		super();
		this.idQuestionEvaluation = idQuestionEvaluation;
	}

	public long getIdQuestionEvaluation() {
		return this.idQuestionEvaluation;
	}

	public void setIdQuestionEvaluation(long idQuestionEvaluation) {
		this.idQuestionEvaluation = idQuestionEvaluation;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public BigDecimal getOrdre() {
		return this.ordre;
	}

	public void setOrdre(BigDecimal ordre) {
		this.ordre = ordre;
	}

	public Qualificatif getQualificatif() {
		return this.qualificatif;
	}

	public void setQualificatif(Qualificatif qualificatif) {
		this.qualificatif = qualificatif;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public RubriqueEvaluation getRubriqueEvaluation() {
		return this.rubriqueEvaluation;
	}

	public void setRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation) {
		this.rubriqueEvaluation = rubriqueEvaluation;
	}

}