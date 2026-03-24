package pweb.demo.service;

import org.springframework.stereotype.Service;
import pweb.demo.dto.CorridaDto;
import pweb.demo.dto.PerguntaDto;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorridaService {
    private Map<Long, CorridaDto> corridasMap = new HashMap<>();
    private long proximoId = 1;

    // Criar nova corrida
    public CorridaDto criar(CorridaDto corridaDto) {
        validarCorridaDto(corridaDto);
        corridaDto.setId(proximoId++);
        if (corridaDto.getPerguntas() == null) {
            corridaDto.setPerguntas(new ArrayList<>());
        }
        corridasMap.put(corridaDto.getId(), corridaDto);
        return corridaDto;
    }

    // Obter corrida por ID
    public CorridaDto obter(long id) {
        validarCorridaExiste(id);
        return corridasMap.get(id);
    }

    // Listar todas as corridas
    public List<CorridaDto> listarTodas() {
        return new ArrayList<>(corridasMap.values());
    }

    // Alterar corrida existente
    public CorridaDto alterar(CorridaDto corridaDto) {
        validarCorridaExiste(corridaDto.getId());
        verificaNomeDuplicado(corridaDto.getTitulo());
        corridasMap.put(corridaDto.getId(), corridaDto);
        return corridaDto;
    }

    // Apagar corrida
    public void apagar(long id) {
        validarCorridaExiste(id);
        corridasMap.remove(id);
    }

    // Validar corrida DTO
    private void validarCorridaDto(CorridaDto corridaDto) {
        verificaNomeDuplicado(corridaDto.getTitulo());
        if (corridaDto.getTempo() == null) {
            throw new IllegalArgumentException("Tempo é obrigatório");
        }
        if (corridaDto.getTitulo() == null || corridaDto.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (corridaDto.getDescricao() == null || corridaDto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
    }

    // Validar se corrida existe
    private void validarCorridaExiste(long id) {
        if (!corridasMap.containsKey(id)) {
            throw new IllegalArgumentException("Corrida com ID " + id + " não encontrada");
        }
    }

    public void verificaNomeDuplicado(String titulo) {
        for (CorridaDto corrida : corridasMap.values()) {
            if (corrida.getTitulo().equalsIgnoreCase(titulo)) {
                throw new IllegalArgumentException("Título de corrida já existe: " + titulo);
            }
        }
    }

    // Adicionar pergunta à corrida
    public void adicionarPergunta(long corridaId, PerguntaDto perguntaDto) {
        CorridaDto corrida = corridasMap.get(corridaId);
        if (corrida != null && corrida.getPerguntas() != null) {
            corrida.getPerguntas().add(perguntaDto);
        }
    }

    // Remover pergunta da corrida
    public void removerPergunta(long corridaId, long perguntaId) {
        CorridaDto corrida = corridasMap.get(corridaId);
        if (corrida != null && corrida.getPerguntas() != null) {
            corrida.getPerguntas().removeIf(p -> p.getId() == perguntaId);
        }
    }

}
