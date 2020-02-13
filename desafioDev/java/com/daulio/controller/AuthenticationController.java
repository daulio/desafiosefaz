package com.daulio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daulio.exception.ApiException;
import com.daulio.response.Response;
import com.daulio.security.dto.JwtAuthenticationDTO;
import com.daulio.security.dto.TokenDTO;
import com.daulio.security.services.impl.JwtUserDetailsServiceImpl;
import com.daulio.security.utils.JwtTokenUtil;
import com.daulio.service.UsuarioService;

import io.swagger.annotations.Api;


@Controller
@RequestMapping("/auth")
@Api(value = "API de autênticação do Usuário", tags = { "Autênticação" })
public class AuthenticationController {

		@Autowired
		private JwtTokenUtil jwtTokenUtil;
		 
		@Autowired
	    private UsuarioService usuarioService;

	    @Autowired
	    private JwtUserDetailsServiceImpl userDetailsService;
		
		@PostMapping
	    public ResponseEntity<TokenDTO> auth(@Valid @RequestBody JwtAuthenticationDTO loginDto, BindingResult result) throws AuthenticationException, ApiException {

	        Response<TokenDTO> response = new Response<>();

	        if (result.hasErrors()) {
	            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }

	        TokenDTO tokenDto = usuarioService.logar(loginDto);
	        if (tokenDto != null) {

	            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getSenha()));
	            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getLogin());

	            tokenDto.setToken(jwtTokenUtil.obterToken(userDetails));
	            response.setData(tokenDto);

	            return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
	        } else {

	            throw new ApiException(HttpStatus.BAD_REQUEST, "Acesso negado!"); 
	        }

	    }

}
