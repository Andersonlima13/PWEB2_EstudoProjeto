package pweb.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pweb.demo.model.Alternativa;
import pweb.demo.dto.AlternativaDto;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativaService {
    private Map<Long, Map<Long, List<Alternativa>>> alternativasPorPergunta = new HashMap<>();
    private long proximoId = 1;

    @Autowired
    private PerguntaService perguntaService;

    // Criar nova alternativa
    public AlternativaDto criar(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();

        // Valida se a pergunta existe
        perguntaService.obter(corridaId, perguntaId);

        validarAlternativaDto(alternativaDto);
        Alternativa alternativa = new Alternativa(proximoId++, alternativaDto.getDescricao(),
                alternativaDto.isCorreta());

        alternativasPorPergunta.computeIfAbsent(perguntaId, k -> new HashMap<>())
                .computeIfAbsent(corridaId, k -> new ArrayList<>())
                .add(alternativa);

        AlternativaDto dto = converterParaDto(alternativa);
        dto.setCorridaId(corridaId);
        dto.setPerguntaId(perguntaId);
        return dto;
    }

    // Obter alternativa específica
    public AlternativaDto obter(long corridaId, long perguntaId, long alternativaId) {
        validarAlternativaExiste(corridaId, perguntaId, alternativaId);
        Alternativa alternativa = obterAlternativa(corridaId, perguntaId, alternativaId);
        return converterParaDto(alternativa);
    }

    // Listar todas as alternativas de uma pergunta
    public List<AlternativaDto> listarPorPergunta(long corridaId, long perguntaId) {
        // Valida se a pergunta existe
        perguntaService.obter(corridaId, perguntaId);

        List<Alternativa> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        return alternativas.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    // Alterar alternativa
    public AlternativaDto alterar(AlternativaDto alternativaDto) {
        long corridaId = alternativaDto.getCorridaId();
        long perguntaId = alternativaDto.getPerguntaId();
        long alternativaId = alternativaDto.getId();

        validarAlternativaExiste(corridaId, perguntaId, alternativaId);
        Alternativa alternativaExistente = obterAlternativa(corridaId, perguntaId, alternativaId);

        alternativaExistente.setDescricao(alternativaDto.getDescricao());
        alternativaExistente.setCorreta(alternativaDto.isCorreta());

        AlternativaDto dto = converterParaDto(alternativaExistente);
        dto.setCorridaId(corridaId);
        dto.setPerguntaId(perguntaId);
        return dto;
    }

    // Apagar alternativa
    public void apagar(long corridaId, long perguntaId, long alternativaId) {
        validarAlternativaExiste(corridaId, perguntaId, alternativaId);

        List<Alternativa> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        alternativas.removeIf(a -> a.getId() == alternativaId);
    }

    // Validar alternativa DTO
    private void validarAlternativaDto(AlternativaDto alternativaDto) {
        if (alternativaDto.getDescricao() == null || alternativaDto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da alternativa é obrigatória");
        }
        if (alternativaDto.isCorreta() == null) {
            throw new IllegalArgumentException("Campo isCorreta é obrigatório");
        }
    }

    // Validar se alternativa existe
    private void validarAlternativaExiste(long corridaId, long perguntaId, long alternativaId) {
        List<Alternativa> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        boolean existe = alternativas.stream().anyMatch(a -> a.getId() == alternativaId);

        if (!existe) {
            throw new IllegalArgumentException(
                    "Alternativa com ID " + alternativaId + " não encontrada na pergunta " + perguntaId);
        }
    }

    // Obter alternativa
    private Alternativa obterAlternativa(long corridaId, long perguntaId, long alternativaId) {
        List<Alternativa> alternativas = alternativasPorPergunta
                .getOrDefault(perguntaId, new HashMap<>())
                .getOrDefault(corridaId, new ArrayList<>());

        return alternativas.stream()
                .filter(a -> a.getId() == alternativaId)
                .findFirst()
                .orElse(null);
    }

    // Converter Alternativa para AlternativaDto
    private AlternativaDto converterParaDto(Alternativa alternativa) {
        return new AlternativaDto(alternativa.getId(), 0, 0, alternativa.getDescricao(), alternativa.isCorreta());
    }
}
