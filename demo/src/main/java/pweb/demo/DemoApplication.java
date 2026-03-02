package pweb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, String> inicio() {
		Map<String, String> resposta = new HashMap<>();
		resposta.put("status", "Aplicação Web iniciada com sucesso");
		resposta.put("versao", "1.0.0");
		resposta.put("endpoints_disponiveis", "/api/login/participante");
		return resposta;
	}

}
