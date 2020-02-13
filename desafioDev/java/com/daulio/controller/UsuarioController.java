package com.daulio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daulio.controller.dto.UsuarioDTO;
import com.daulio.exception.ApiException;
import com.daulio.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController()
@RequestMapping("/usuario")
@Api(value = "API acesso aos dados do Usuário", tags = { "Usuário" })
public class UsuarioController {
  
	@Autowired
	private UsuarioService usuarioService;
	

	@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Authorization Bearer",
                required = true,
                dataType = "string",
                paramType = "header")
    })
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> salvarNovoUsuario(@RequestBody @Valid UsuarioDTO dto) 
			throws Exception {
		
		UsuarioDTO usuarioDTO = usuarioService.salvar(dto);
		return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
	}

	
	@CrossOrigin
	@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Authorization Bearer",
                required = true,
                dataType = "string",
                paramType = "header")
    })
	@PatchMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> alterarUsuario(@RequestBody @Valid UsuarioDTO dto) 
			throws Exception {
		UsuarioDTO usuarioDTO = usuarioService.updateUsuario(dto);
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Authorization Bearer",
                required = true,
                dataType = "string",
                paramType = "header")
    })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UsuarioDTO> getUsuarioPorId(@PathVariable Long id) throws ApiException {
		
		UsuarioDTO find = usuarioService.getUsuarioPorId(id);
		return new ResponseEntity<>(find, HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Authorization Bearer",
                required = true,
                dataType = "string",
                paramType = "header")
    })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UsuarioDTO>> getListarTodos(){
		List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
		
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Authorization Bearer",
                required = true,
                dataType = "string",
                paramType = "header")
    })
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteImagingExaminationRequest(@PathVariable Long id) throws ApiException  {
		usuarioService.deleteUsuario(id);
	}
}
