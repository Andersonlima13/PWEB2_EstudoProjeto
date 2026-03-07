package pweb.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pweb.demo.dto.PerguntaDto;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerguntaService {
    private Map<Long, Map<Long, List<PerguntaDto>>> perguntasPorCorrida = new HashMap<>();
    private long proximoId = 1;

    @Autowired
    private CorridaService corridaService;

    // Criar nova pergunta
    public PerguntaDto criar(PerguntaDto perguntaDto) {
        long corridaId = perguntaDto.getCorridaId();

        // Valida se a corrida existe
        corridaService.obter(corridaId);

        validarPerguntaDto(perguntaDto);
        perguntaDto.setId(proximoId++);

        perguntasPorCorrida.computeIfAbsent(corridaId, k -> new HashMap<>())
                .computeIfAbsent(corridaId, k -> new ArrayList<>())
                .add(perguntaDto);

        // Adiciona objeto pergunta à corrida
        corridaService.adicionarPergunta(corridaId, perguntaDto);

        return perguntaDto;
    }

    // Obter pergunta específica
    public PerguntaDto obter(long corridaId, long perguntaId) {
        validarPerguntaExiste(corridaId, perguntaId);
        List<PerguntaDto> perguntas = perguntasPorCorrida
                .getOrDefault(corridaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        return perguntas.stream()
                .filter(p -> p.getId() == perguntaId)
                .findFirst()
                .orElse(null);
    }

    // Listar todas as perguntas de uma corrida
    public List<PerguntaDto> listarPorCorrida(long corridaId) {
        // Valida se a corrida existe
        corridaService.obter(corridaId);

        return new ArrayList<>(perguntasPorCorrida
                .getOrDefault(corridaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>()));
    }

    // Alterar pergunta
    public PerguntaDto alterar(PerguntaDto perguntaDto) {
        long corridaId = perguntaDto.getCorridaId();
        long perguntaId = perguntaDto.getId();

        validarPerguntaExiste(corridaId, perguntaId);

        List<PerguntaDto> perguntas = perguntasPorCorrida
                .getOrDefault(corridaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        perguntas.stream()
                .filter(p -> p.getId() == perguntaId)
                .findFirst()
                .ifPresent(p -> {
                    p.setEnunciado(perguntaDto.getEnunciado());
                    p.setRespostaCorreta(perguntaDto.getRespostaCorreta());
                    p.setAlternativas(perguntaDto.getAlternativas());
                });

        return perguntaDto;
    }

    // Apagar pergunta
    public void apagar(long corridaId, long perguntaId) {
        validarPerguntaExiste(corridaId, perguntaId);

        List<PerguntaDto> perguntas = perguntasPorCorrida
                .getOrDefault(corridaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        perguntas.removeIf(p -> p.getId() == perguntaId);

        // Remove ID da pergunta da corrida
        corridaService.removerPergunta(corridaId, perguntaId);
    }

    // Validar pergunta DTO
    private void validarPerguntaDto(PerguntaDto perguntaDto) {
        if (perguntaDto.getEnunciado() == null || perguntaDto.getEnunciado().trim().isEmpty()) {
            throw new IllegalArgumentException("Enunciado é obrigatório");
        }
        if (perguntaDto.getRespostaCorreta() <= 0) {
            throw new IllegalArgumentException("Resposta correta é obrigatória");
        }
    }

    // Validar se pergunta existe na corrida
    private void validarPerguntaExiste(long corridaId, long perguntaId) {
        List<PerguntaDto> perguntas = perguntasPorCorrida
                .getOrDefault(corridaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        boolean existe = perguntas.stream().anyMatch(p -> p.getId() == perguntaId);

        if (!existe) {
            throw new IllegalArgumentException(
                    "Pergunta com ID " + perguntaId + " não encontrada na corrida " + corridaId);
        }
    }
}
