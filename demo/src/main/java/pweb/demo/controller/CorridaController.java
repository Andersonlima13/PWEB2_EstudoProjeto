package pweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.demo.dto.CorridaDto;
import pweb.demo.service.CorridaService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/corridas")
public class CorridaController {

    @Autowired
    private CorridaService corridaService;

    // Criar nova corrida
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CorridaDto corridaDto) {
        try {
            CorridaDto novaCorridag = corridaService.criar(corridaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCorridag);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Obter corrida por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obter(@PathVariable long id) {
        try {
            CorridaDto corrida = corridaService.obter(id);
            return ResponseEntity.ok(corrida);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Listar todas as corridas
    @GetMapping
    public ResponseEntity<List<CorridaDto>> listar() {
        List<CorridaDto> corridas = corridaService.listarTodas();
        return ResponseEntity.ok(corridas);
    }

    // Alterar corrida
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody CorridaDto corridaDto) {
        try {
            corridaDto.setId(id);
            CorridaDto corridaModificada = corridaService.alterar(corridaDto);
            return ResponseEntity.ok(corridaModificada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    // Apagar corrida
    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagar(@PathVariable long id) {
        try {
            corridaService.apagar(id);
            return ResponseEntity.ok(Map.of("mensagem", "Corrida com ID " + id + " foi deletada com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}
