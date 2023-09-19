package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // retornar Json o Xml o texto
@RequestMapping("/medicos") //le indico la ruta de http que va a seguir el metodo
public class MedicoController {
	
	@Autowired
	//Es para no realizar una implementacion del objeto de forma automatica, osea medicoRepository = new ....
	//Esta anotación no se recomienda para prubeas unitarias
	private MedicoRepository medicoRepository;

	//@requestBody es para indicarle al codigo que tome el body que esta enviando el request
	@PostMapping
	public void RegistrarMedico(@RequestBody @Valid DatosRegistroMedico datos) {
		medicoRepository.save(new Medico(datos));
	}

	@GetMapping // sirve para especificar el metodo cuando se realiza una solicitud GET
	public Page<DatosListadoMedico> ListadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
		return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
	}

	@PutMapping
	@Transactional //Nos permite guardar en la base de datos los cambios que se realizen al finalizar el metodo
	public void ActualizarMedico(@RequestBody @Valid DatosActualizarMedico datos){
		Medico medico = medicoRepository.getReferenceById(datos.id());
		medico.actualizarDatos(datos);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void EliminarMedico(@PathVariable Long id){
		Medico medico = medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
	}
	/*
	public void EliminarMedico(@PathVariable Long id){
		Medico medico = medicoRepository.getReferenceById(id);
		medicoRepository.delete(medico);
	}
	*/
}
