package med.voll.api.controller;

import med.voll.api.domain.usuarios.DatosAutenticacionUsuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class AutenticacionController {

    @PostMapping
    public ResponseEntity AutenticarUsuario(DatosAutenticacionUsuarios datosAutenticacionUsuarios){

    }

}
