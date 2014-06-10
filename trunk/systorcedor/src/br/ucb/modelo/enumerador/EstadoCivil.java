package br.ucb.modelo.enumerador;

public enum EstadoCivil {
	SOLTEIRO,
	CASADO,
	SEPARADO,
	DIVORCIADO,
	VIUVO;
	
	public EstadoCivil[] getValores() {
		return EstadoCivil.values();
	}
	
}