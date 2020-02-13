package com.daulio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.daulio.controller.dto.TelefoneDTO;
import com.daulio.controller.dto.UsuarioDTO;
import com.daulio.enums.TipoTelefone;
import com.daulio.security.dto.JwtAuthenticationDTO;
import com.daulio.security.dto.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DesafioDevApplicationTests {
	
	@Autowired private MockMvc mockMvc;
	private static TokenDTO tokenDTO = new TokenDTO();
	private static UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@BeforeAll
	public static void init(){
		
		usuarioDTO.setNome("JAMESBOND");
		usuarioDTO.setEmail("jamesbond@email.com.br");
		usuarioDTO.setSenha("12345");
		
		List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
		TelefoneDTO fone = new TelefoneDTO();
		fone.setDdd("81");
		fone.setNumero("99999999");
		fone.setTipoTelefone(TipoTelefone.CELULAR);
		telefones.add(fone);
		
		usuarioDTO.setTelefones(telefones);
	}
	
	@Test 
	public void test1() {
		JwtAuthenticationDTO dto = new JwtAuthenticationDTO();
		dto.setLogin("admin");
		dto.setSenha("123456");
			
		try {		
			MvcResult result = this.mockMvc.perform(post("/auth")
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(dto)))	
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
			
			tokenDTO.setIdUsuario(new ObjectMapper().readValue(result.getResponse().getContentAsString(), TokenDTO.class).getIdUsuario());
			tokenDTO.setToken(new ObjectMapper().readValue(result.getResponse().getContentAsString(), TokenDTO.class).getToken());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}

		
	}

	@Test
	public void test2() {
	
		try {		
			MvcResult result = this.mockMvc.perform(post("/usuario/")
					.header("Authorization", "Bearer " + tokenDTO.getToken())
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(usuarioDTO)))	
					.andDo(print())
					.andExpect(status().isCreated())
					.andReturn();
			
			usuarioDTO.setId(new ObjectMapper().readValue(result.getResponse().getContentAsString(), UsuarioDTO.class).getId());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test3() {
		try {		
			this.mockMvc.perform(patch("/usuario/")
					.header("Authorization", "Bearer " + tokenDTO.getToken())
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(usuarioDTO)))	
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test4() {
		try {	
			this.mockMvc.perform(get("/usuario/{id}", usuarioDTO.getId().intValue())
					.content(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + tokenDTO.getToken()))
			        .andDo(print())
			        .andExpect(status().isOk())
					.andExpect(jsonPath("$.id", is(usuarioDTO.getId().intValue())))
					.andExpect(jsonPath("$.telefones", isA(List.class)));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test5() {
		try {	
			this.mockMvc.perform(get("/usuario/")
					.content(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + tokenDTO.getToken()))
					.andDo(print())
					.andExpect(status().isOk())
					;
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test6() {
		try {		
			this.mockMvc.perform(delete("/usuario/{id}", usuarioDTO.getId().intValue())
					.accept(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + tokenDTO.getToken()))
					.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
