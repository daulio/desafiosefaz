package com.daulio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.daulio.controller.dto.TelefoneDTO;
import com.daulio.controller.dto.UsuarioDTO;
import com.daulio.entity.Telefone;
import com.daulio.entity.Usuario;
import com.daulio.exception.ApiException;
import com.daulio.repository.TelefoneRepository;
import com.daulio.repository.UsuarioRepository;
import com.daulio.security.dto.JwtAuthenticationDTO;
import com.daulio.security.dto.TokenDTO;


@Service
public class UsuarioService {
	public String mensagem;
    public HttpStatus httpStatus;
	
	@Autowired UsuarioRepository usuarioRepository;
	
	@Autowired TelefoneRepository telefoneRepository;
	
	public TokenDTO logar(JwtAuthenticationDTO loginDto) {
		
        Optional<Usuario> usrBD = usuarioRepository.findByNome(loginDto.getLogin());
        
		if (usrBD.isPresent()) {
			
			if (usrBD.get().getSenha().equals(loginDto.getSenha())) {

                return new TokenDTO(null, usrBD.get().getId());
            }

            mensagem = "A senha informada está incorreta!";
            httpStatus = HttpStatus.BAD_REQUEST;
            return null;
        }

        mensagem = "Usuario informado não existe!";
        httpStatus = HttpStatus.BAD_REQUEST;
        return null;

    }

	
	public UsuarioDTO salvar(UsuarioDTO dto) {
		Usuario usuario = usuarioRepository.save(dto.toUsuario());
		List<Telefone> telefones = new ArrayList<Telefone>();
		for (TelefoneDTO telefone : dto.getTelefones()) {
			Telefone tel = telefone.toTelefone();
			tel.setUsuario(usuario);
			telefoneRepository.save(tel);
			telefones.add(tel);
		}
		usuario.setTelefones(telefones);
		return new UsuarioDTO(usuario);
		
	}
	
	public List<UsuarioDTO> listarUsuarios(){
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario usuario : usuarios) {
			usuario.setTelefones(telefoneRepository.findByUsuario(usuario));
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		return usuariosDTO;
	}
	
	public UsuarioDTO getUsuarioPorId(Long id) throws ApiException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if(!usuarioOpt.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!"); 
		}
		List<Telefone> telefones = telefoneRepository.findByUsuario(usuarioOpt.get());
		usuarioOpt.get().setTelefones(telefones);
		return new UsuarioDTO(usuarioOpt.get());
	}
	
	public UsuarioDTO updateUsuario(UsuarioDTO dto) throws ApiException {
		Optional<Usuario> findOpt = usuarioRepository.findById(dto.getId());
		if(!findOpt.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!"); 
		}
		Usuario usuario = usuarioRepository.save(dto.toUsuario());
		List<Telefone> telefones = new ArrayList<Telefone>();
		for (TelefoneDTO telefone : dto.getTelefones()) {
			Telefone tel = telefone.toTelefone();
			tel.setUsuario(usuario);
			telefoneRepository.save(tel);
			telefones.add(tel);
		}
		usuario.setTelefones(telefones);
		return new UsuarioDTO(usuario);
	}
	
	public void deleteUsuario(Long id) throws ApiException {
		Optional<Usuario> findOpt = usuarioRepository.findById(id);
		if(!findOpt.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!"); 
		}
		List<Telefone> telefones = telefoneRepository.findByUsuario(findOpt.get());
		for (Telefone telefone : telefones) {
			telefoneRepository.delete(telefone);
		}
		usuarioRepository.delete(findOpt.get());
	}

}
