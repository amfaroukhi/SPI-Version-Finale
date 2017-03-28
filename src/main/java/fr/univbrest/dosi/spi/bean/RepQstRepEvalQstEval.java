package fr.univbrest.dosi.spi.bean;

public class RepQstRepEvalQstEval {

	private long idReponseEvaluation;
	
	private ReponseQuestion reponseQuestion;
	
	private long idQuestionEvaluation;

	public RepQstRepEvalQstEval(long idReponseEvaluation,
			ReponseQuestion reponseQuestion,
			long idQuestionEvaluation) {
		super();
		this.idReponseEvaluation = idReponseEvaluation;
		this.reponseQuestion = reponseQuestion;
		this.idQuestionEvaluation = idQuestionEvaluation;
	}

	public RepQstRepEvalQstEval() {
		
	}
	
	public long getIdReponseEvaluation() {
		return idReponseEvaluation;
	}

	public void setIdReponseEvaluation(long idReponseEvaluation) {
		this.idReponseEvaluation = idReponseEvaluation;
	}

	public ReponseQuestion getReponseQuestion() {
		return reponseQuestion;
	}

	public void setReponseQuestion(ReponseQuestion reponseQuestion) {
		this.reponseQuestion = reponseQuestion;
	}

	public long getIdQuestionEvaluation() {
		return idQuestionEvaluation;
	}

	public void setIdQuestionEvaluation(long idQuestionEvaluation) {
		this.idQuestionEvaluation = idQuestionEvaluation;
	}
	
	
	
}
