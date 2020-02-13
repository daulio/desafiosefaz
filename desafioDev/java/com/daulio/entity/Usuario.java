package com.daulio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario", updatable = false)
	private Long id;
	
	private String nome;
	
	private String email;
	
	@Column(name = "senha", updatable = false)
	private String senha;
	
	@Transient
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Telefone> telefones;
	
	

}
