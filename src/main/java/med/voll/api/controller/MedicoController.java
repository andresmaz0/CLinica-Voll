package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	//Es para no realizar una implementacion del objeto
	//no se recomienda para prubeas unitarias
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public void RegistrarMedico(@RequestBody DatosRegistroMedico datos) {
		medicoRepository.save(new Medico(datos));
	}
	
}
