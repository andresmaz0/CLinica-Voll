package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	//Es para no realizar una implementacion del objeto
	//no se recomienda para prubeas unitarias
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public void RegistrarMedico(@RequestBody @Valid DatosRegistroMedico datos) {
		medicoRepository.save(new Medico(datos));
	}

	@GetMapping
	public Page<DatosListadoMedico> ListadoMedicos(Pageable paginacion){
		return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
	}
	
}
