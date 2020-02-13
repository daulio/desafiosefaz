package com.daulio.desafiofront.dto;

import com.daulio.desafiofront.enums.TipoTelefone;

public class TelefoneDTO {
    private Long id;
	
    private String ddd;
	
    private String numero;
	
    private TipoTelefone tipoTelefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
    
    
}
