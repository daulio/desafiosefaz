package com.daulio.desafiofront.enums;

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
	
	public String getName() {
		return getByValue(value).name();
	}
	
	public String toString() {
		return value;
	}

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
