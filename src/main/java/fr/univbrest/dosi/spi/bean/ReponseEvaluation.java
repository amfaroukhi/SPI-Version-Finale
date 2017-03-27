package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;


/**
 * The persistent class for the REPONSE_EVALUATION database table.
 * 
 */
@Entity
@Table(name="REPONSE_EVALUATION")
@NamedQuery(name="ReponseEvaluation.findAll", query="SELECT r FROM ReponseEvaluation r")
public class ReponseEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq_gen6")
	@SequenceGenerator(name="my_seq_gen6", sequenceName="RPE_SEQ")
	@Column(name="ID_REPONSE_EVALUATION")
	private long idReponseEvaluation;

	private String commentaire;

	@Column(name="NO_ETUDIANT")
	private String noEtudiant;

	private String nom;

	private String prenom;

	//uni-directional many-to-one association to Evaluation
	 @RestResource(exported=false)
	@ManyToOne
	@JoinColumn(name="ID_EVALUATION")
	private Evaluation evaluation;

	public ReponseEvaluation() {
	}

	public long getIdReponseEvaluation() {
		return this.idReponseEvaluation;
	}

	public void setIdReponseEvaluation(long idReponseEvaluation) {
		this.idReponseEvaluation = idReponseEvaluation;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getNoEtudiant() {
		return this.noEtudiant;
	}

	public void setNoEtudiant(String noEtudiant) {
		this.noEtudiant = noEtudiant;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public void setIdEvaluation(long id){
		this.evaluation.setIdEvaluation(id);
	}

}