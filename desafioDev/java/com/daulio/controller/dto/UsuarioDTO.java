package com.daulio.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.daulio.entity.Telefone;
import com.daulio.entity.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private List<TelefoneDTO> telefones;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		List<TelefoneDTO> telefones = new ArrayList<>();
		for (Telefone telefone : usuario.getTelefones()) {
			telefones.add(new TelefoneDTO(telefone));
		}
		this.telefones = telefones;
	}
	
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		List<Telefone> telefones = new ArrayList<>();
		for (TelefoneDTO telefone : this.telefones) {
			telefones.add(telefone.toTelefone());
		}
		usuario.setTelefones(telefones);
		return usuario;
	}
}
