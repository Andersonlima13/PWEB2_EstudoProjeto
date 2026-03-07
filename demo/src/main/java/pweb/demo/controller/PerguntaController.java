package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.demo.dto.PerguntaDto;
import pweb.demo.service.PerguntaService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/corridas/{corridaId}/pergunta")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    // Criar nova pergunta
    @PostMapping
    public ResponseEntity<?> criar(@PathVariable long corridaId, @RequestBody PerguntaDto perguntaDto) {
        try {
            perguntaDto.setCorridaId(corridaId);
            PerguntaDto novaPergunta = perguntaService.criar(perguntaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaPergunta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Obter pergunta específica
    @GetMapping("/{perguntaId}")
    public ResponseEntity<?> obter(@PathVariable long corridaId, @PathVariable long perguntaId) {
        try {
            PerguntaDto pergunta = perguntaService.obter(corridaId, perguntaId);
            return ResponseEntity.ok(pergunta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Listar todas as perguntas da corrida
    @GetMapping
    public ResponseEntity<?> listar(@PathVariable long corridaId) {
        try {
            List<PerguntaDto> perguntas = perguntaService.listarPorCorrida(corridaId);
            return ResponseEntity.ok(perguntas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Alterar pergunta
    @PutMapping("/{perguntaId}")
    public ResponseEntity<?> alterar(@PathVariable long corridaId, @PathVariable long perguntaId,
            @RequestBody PerguntaDto perguntaDto) {
        try {
            perguntaDto.setCorridaId(corridaId);
            perguntaDto.setId(perguntaId);
            PerguntaDto perguntaModificada = perguntaService.alterar(perguntaDto);
            return ResponseEntity.ok(perguntaModificada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Apagar pergunta
    @DeleteMapping("/{perguntaId}")
    public ResponseEntity<?> apagar(@PathVariable long corridaId, @PathVariable long perguntaId) {
        try {
            perguntaService.apagar(corridaId, perguntaId);
            return ResponseEntity.ok(Map.of("mensagem", "Pergunta com ID " + perguntaId + " foi deletada com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}
