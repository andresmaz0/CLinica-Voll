package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.Infra.security.DatosJwtToken;
import med.voll.api.Infra.security.TokenService;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuarios;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class AutenticacionController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity AutenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuarios datosAutenticacionUsuarios){
        Authentication Authentication_token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuarios.login(),
                datosAutenticacionUsuarios.clave());
        var UsuarioAutenticado = authenticationManager.authenticate(Authentication_token);
        var Jwt_token = tokenService.generarToken((Usuario) UsuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(Jwt_token));
    }

}
