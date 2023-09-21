package med.voll.api.domain.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


@Table(name = "medicos")
@Entity(name = "Medico")

@Getter //lombok me genera automaticamente los getters de mis atributos
@NoArgsConstructor //lombok me crea un constructor sin atributos
@AllArgsConstructor //lombok me crea un constructor con todos los atributos
@EqualsAndHashCode(of = "id") //lombok sobre escribe el metodo hashcode y equals por el programador
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String telefono;
	private String documento;
	private Boolean activo;

	@Enumerated(EnumType.STRING)//Esto indica que los datos de el enum Especialidad seran guardados como Strirng
	private Especialidad especialidad;
	@Embedded
	/*indica que los campos de la entidad incrustada deben ser mapeados
	como parte de la entidad actual en lugar de como una entidad separada.
	 */
	private Direccion direccion;

	public Medico(DatosRegistroMedico datos) {
		this.activo = true;
		this.nombre = datos.nombre();
		this.email = datos.email();
		this.telefono = datos.telefono();
		this.documento = datos.documento();
		this.especialidad = datos.especialidad();
		this.direccion = new Direccion(datos.direccion());
	}

    public void actualizarDatos(DatosActualizarMedico datos) {
		if(datos.nombre() != null){
			this.nombre = datos.nombre();
		}
		if(datos.documento() != null){
			this.documento = datos.documento();
		}
		if(datos.direccion() != null) {
			this.direccion = direccion.actualizarDatos(datos.direccion());
		}
    }

	public void desactivarMedico() {
		this.activo = false;
	}
}
