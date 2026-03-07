package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.demo.dto.AlternativaDto;
import pweb.demo.service.AlternativaService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/corridas/{corridaId}/pergunta/{perguntaId}/alternativa")
public class AlternativaController {

    @Autowired
    private AlternativaService alternativaService;

    // Criar nova alternativa
    @PostMapping
    public ResponseEntity<?> criar(@PathVariable long corridaId, @PathVariable long perguntaId,
            @RequestBody AlternativaDto alternativaDto) {
        try {
            alternativaDto.setCorridaId(corridaId);
            alternativaDto.setPerguntaId(perguntaId);
            AlternativaDto novaAlternativa = alternativaService.criar(alternativaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAlternativa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Obter alternativa específica
    @GetMapping("/{alternativaId}")
    public ResponseEntity<?> obter(@PathVariable long corridaId, @PathVariable long perguntaId,
            @PathVariable long alternativaId) {
        try {
            AlternativaDto alternativaDto = new AlternativaDto();
            alternativaDto.setCorridaId(corridaId);
            alternativaDto.setPerguntaId(perguntaId);
            alternativaDto.setId(alternativaId);

            AlternativaDto alternativa = alternativaService.obter(alternativaDto);
            return ResponseEntity.ok(alternativa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Listar todas as alternativas da pergunta
    @GetMapping
    public ResponseEntity<?> listar(@PathVariable long corridaId, @PathVariable long perguntaId) {
        try {
            AlternativaDto alternativaDto = new AlternativaDto();
            alternativaDto.setCorridaId(corridaId);
            alternativaDto.setPerguntaId(perguntaId);

            List<AlternativaDto> alternativas = alternativaService.listarPorPergunta(alternativaDto);
            return ResponseEntity.ok(alternativas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Alterar alternativa
    @PutMapping("/{alternativaId}")
    public ResponseEntity<?> alterar(@PathVariable long corridaId, @PathVariable long perguntaId,
            @PathVariable long alternativaId, @RequestBody AlternativaDto alternativaDto) {
        try {
            alternativaDto.setCorridaId(corridaId);
            alternativaDto.setPerguntaId(perguntaId);
            alternativaDto.setId(alternativaId);
            AlternativaDto alternativaModificada = alternativaService.alterar(alternativaDto);
            return ResponseEntity.ok(alternativaModificada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Apagar alternativa
    @DeleteMapping("/{alternativaId}")
    public ResponseEntity<?> apagar(@PathVariable long corridaId, @PathVariable long perguntaId,
            @PathVariable long alternativaId) {
        try {
            AlternativaDto alternativaDto = new AlternativaDto();
            alternativaDto.setCorridaId(corridaId);
            alternativaDto.setPerguntaId(perguntaId);
            alternativaDto.setId(alternativaId);

            alternativaService.apagar(alternativaDto);
            return ResponseEntity
                    .ok(Map.of("mensagem", "Alternativa com ID " + alternativaId + " foi deletada com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}