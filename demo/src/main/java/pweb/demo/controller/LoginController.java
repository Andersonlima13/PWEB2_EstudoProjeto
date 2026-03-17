// Participante e administrador podem fazer login

package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pweb.demo.dto.ParticipanteDto;
import pweb.demo.model.Participante;
import pweb.demo.service.LoginService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/participante")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ParticipanteDto dto) {
        Participante participante = loginService.autenticar(dto);
        return ResponseEntity.ok(participante);
    }

    @PostMapping("/registrar")
    public String registrar(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @PostMapping("/logout")
    public String logout(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

}
