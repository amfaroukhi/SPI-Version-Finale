package fr.univbrest.dosi.spi.bean;


public class LoginPwd {


	private String loginConnection;

	
	private String motPasse;

	public LoginPwd(String loginConnection, String motPasse) {
		super();
		this.loginConnection = loginConnection;
		this.motPasse = motPasse;
	}
	
	public LoginPwd() {
	
	}

	public String getLoginConnection() {
		return loginConnection;
	}

	public void setLoginConnection(String loginConnection) {
		this.loginConnection = loginConnection;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	
	
	
	
}