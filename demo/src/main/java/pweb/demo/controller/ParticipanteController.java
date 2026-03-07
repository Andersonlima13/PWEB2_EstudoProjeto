package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.demo.dto.ParticipanteDto;
import pweb.demo.service.ParticipanteService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    // Criar novo participante
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ParticipanteDto participanteDto) {
        try {
            ParticipanteDto novoParticipante = participanteService.criar(participanteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoParticipante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Obter participante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obter(@PathVariable long id) {
        try {
            ParticipanteDto participante = participanteService.obter(id);
            return ResponseEntity.ok(participante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Listar todos os participantes
    @GetMapping
    public ResponseEntity<List<ParticipanteDto>> listar() {
        List<ParticipanteDto> participantes = participanteService.listarTodos();
        return ResponseEntity.ok(participantes);
    }

    // Alterar participante
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody ParticipanteDto participanteDto) {
        try {
            participanteDto.setId(id);
            ParticipanteDto participanteModificado = participanteService.alterar(participanteDto);
            return ResponseEntity.ok(participanteModificado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Apagar participante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagar(@PathVariable long id) {
        try {
            participanteService.apagar(id);
            return ResponseEntity.ok(Map.of("mensagem", "Participante com ID " + id + " foi deletado com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}
