package com.daulio.controller.dto;

import org.springframework.validation.annotation.Validated;

import com.daulio.entity.Telefone;
import com.daulio.enums.TipoTelefone;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Validated
@NoArgsConstructor
public class TelefoneDTO {

	private Long id;
	
	private String ddd;
	
	private String numero;
	
	private TipoTelefone tipoTelefone;
	
	public TelefoneDTO(Telefone telefone) {
		this.id = telefone.getId();
		this.ddd = telefone.getDdd();
		this.numero = telefone.getNumero();
		this.tipoTelefone = TipoTelefone.getByValue(telefone.getTipoTelefone());
	}
	
	public Telefone toTelefone() {
		Telefone fone = new Telefone();
		fone.setId(this.id);
		fone.setDdd(this.ddd);
		fone.setNumero(this.numero);
		fone.setTipoTelefone(this.tipoTelefone==null?null:this.tipoTelefone.value);
		return fone;
	}
}
