// Participante e administrador podem fazer login

package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pweb.demo.model.Participante;
import pweb.demo.service.LoginService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/participante")
    public ResponseEntity<Map<String, String>> loginParticipante(@RequestBody Map<String, String> request) {
        try {
            String nome = request.get("nome");
            Participante participante = loginService.autenticar(nome);

            Map<String, String> response = new HashMap<>();
            response.put("nome", participante.getNome());

            // modificar isso aqui, tratar o erro especifico
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("erro", e.getMessage()));
        }
    }
}
