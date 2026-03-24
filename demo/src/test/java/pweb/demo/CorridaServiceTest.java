package pweb.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pweb.demo.dto.CorridaDto;
import pweb.demo.service.CorridaService;

@SpringBootTest
public class CorridaServiceTest {

    @Test
    void deveCriarUmaCorrida() {
        CorridaService corridaService = new CorridaService();

        CorridaDto dto = new CorridaDto();
        dto.setTitulo("Corrida 1");
        dto.setTempo(12);
        dto.setDescricao("Esse é um teste da corrida 1");
        CorridaDto result = corridaService.criar(dto);

        assertNotNull(result.getId());
        assertEquals("Corrida 1", result.getTitulo());
        assertNotNull(result.getPerguntas());

    }

}
