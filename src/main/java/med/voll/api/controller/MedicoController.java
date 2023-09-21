package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController // retornar Json o Xml o texto
@RequestMapping("/medicos") //le indico la ruta de http que va a seguir el metodo
public class MedicoController {
	
	@Autowired
	//Es para no realizar una implementacion del objeto de forma automatica, osea medicoRepository = new ....
	//Esta anotación no se recomienda para prubeas unitarias
	private MedicoRepository medicoRepository;

	//@requestBody es para indicarle al codigo que tome el body que esta enviando el request
	@PostMapping
	public ResponseEntity<DatosRespuestaMedico> RegistrarMedico(@RequestBody @Valid DatosRegistroMedico datos,
										  UriComponentsBuilder uriComponentsBuilder) {
		Medico medico = medicoRepository.save(new Medico(datos));
		DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento()));
		URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(url).body(datosRespuestaMedico);
	}

	@GetMapping // sirve para especificar el metodo cuando se realiza una solicitud GET
	public ResponseEntity<Page<DatosListadoMedico>> ListadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
		return ResponseEntity.ok(medicoRepository.findAll(paginacion).map(DatosListadoMedico::new));
	}

	@PutMapping
	@Transactional //Nos permite guardar en la base de datos los cambios que se realizen al finalizar el metodo
	public ResponseEntity ActualizarMedico(@RequestBody @Valid DatosActualizarMedico datos){
		Medico medico = medicoRepository.getReferenceById(datos.id());
		medico.actualizarDatos(datos);
		return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento())));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity EliminarMedico(@PathVariable Long id){
		Medico medico = medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
		return ResponseEntity.noContent().build();
		//build convierte el codigo Http a un ResponseEntity
	}

	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento()));
		return ResponseEntity.ok(datosMedico);
	}
}
