package med.voll.api.direccion;

import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {
	
	private String calle;
	private Integer numero;
	private String complemento;
	private String distrito;
	private String ciudad;
	
}
