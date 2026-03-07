package pweb.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pweb.demo.dto.AlternativaDto;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlternativaService {
    private Map<Long, Map<Long, List<AlternativaDto>>> alternativasPorPergunta = new HashMap<>();
    private long proximoId = 1;

    @Autowired
    private PerguntaService perguntaService;

    // Criar nova alternativa
    public AlternativaDto criar(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();

        // Valida se a pergunta existe
        perguntaService.obter(corridaId, perguntaId);

        alternativaDto.setId(proximoId++);

        alternativasPorPergunta.computeIfAbsent(perguntaId, k -> new HashMap<>())
                .computeIfAbsent(corridaId, k -> new ArrayList<>())
                .add(alternativaDto);

        // Adiciona alternativa à pergunta
        perguntaService.adicionarAlternativa(corridaId, perguntaId, alternativaDto);

        return alternativaDto;
    }

    // Obter alternativa específica
    public AlternativaDto obter(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();
        long alternativaId = alternativaDto.getId();

        return obterAlternativaDto(corridaId, perguntaId, alternativaId);
    }

    // Listar todas as alternativas de uma pergunta
    public List<AlternativaDto> listarPorPergunta(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();

        // Valida se a pergunta existe
        perguntaService.obter(corridaId, perguntaId);

        return new ArrayList<>(alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>()));
    }

    // Alterar alternativa
    public AlternativaDto alterar(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();
        long alternativaId = alternativaDto.getId();

        List<AlternativaDto> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        alternativas.stream()
                .filter(a -> a.getId() == alternativaId)
                .findFirst()
                .ifPresent(a -> {
                    a.setDescricao(alternativaDto.getDescricao());
                    a.setCorreta(alternativaDto.isCorreta());
                });

        return alternativaDto;
    }

    // Apagar alternativa
    public void apagar(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();
        long alternativaId = alternativaDto.getId();

        List<AlternativaDto> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        alternativas.removeIf(a -> a.getId() == alternativaId);

        // Remove alternativa da pergunta
        perguntaService.removerAlternativa(corridaId, perguntaId, alternativaDto);
        boolean existe = alternativas.stream().anyMatch(a -> a.getId() == alternativaId);

        if (!existe) {
            throw new IllegalArgumentException(
                    "Alternativa com ID " + alternativaId + " não encontrada na pergunta " + perguntaId);
        }
    }

    // Obter alternativa DTO
    private AlternativaDto obterAlternativaDto(long corridaId, long perguntaId, long alternativaId) {
        List<AlternativaDto> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        return alternativas.stream()
                .filter(a -> a.getId() == alternativaId)
                .findFirst()
                .orElse(null);
    }
}
