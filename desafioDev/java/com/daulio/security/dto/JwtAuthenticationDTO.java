package com.daulio.security.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
public class JwtAuthenticationDTO {
	
	@NotEmpty(message = "Login é de preenchimento obrigatório.")
	private String login;
	@NotEmpty(message = "Senha é de preenchimento obrigatório.")
	private String senha;
	


}
