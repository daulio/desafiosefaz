package com.daulio.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTelefone {

	RESIDENCIAL("RE"),
	CELULAR("CE"),
	COMERCIAL("CO"),
	RECADO("RC"),
	INVALID("INVALIDO");
	
	public final String value;

	private TipoTelefone(String value) {
		this.value = value;
	}
	
	@JsonValue
	public String getName() {
		return getByValue(value).name();
	}
	
	@Override
	public String toString() {
		return value;
	}

	@JsonCreator
	public static TipoTelefone getByName(String name) {  
		if (name != null){
			try {
				return valueOf(name);
			}catch (IllegalArgumentException e) {
				return INVALID;
			}
		}
		return null;
	}
	
	public static TipoTelefone getByValue(String value) {  
		if (value != null){
	        for (int i = 0; i < TipoTelefone.values().length; i++) {
				if (value.equals(TipoTelefone.values()[i].value))
					return TipoTelefone.values()[i];
			}
		}else {
			return null;
		}
		return INVALID;
	}
	
}
