package com.daulio.security.dto;

import java.io.Serializable;


public class TokenDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	private Long idUsuario;
	
	public TokenDTO() {
		
	}
	
	public TokenDTO(String token, Long idUsuario) {
		super();
		this.token = token;
		this.idUsuario = idUsuario;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	

}
