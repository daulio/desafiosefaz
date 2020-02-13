package com.daulio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String ddd;
	
	private String numero;
	
	private String tipoTelefone;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;
	

}
