package br.com.automacao;
/*
Verifica se o Spring sobe: Você não deve executar o ColetarDeclaracoesTest dentro da classe AutomacaoApplicationTests. 
Essa classe é um teste padrão do Spring Boot. Ela serve apenas para verificar se a aplicação 
inicia corretamente. É apenas um teste estrutural, não faz automação, não coleta dados.
 */
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutomacaoApplicationTests {

	@Test
	void contextLoads() {
	}

}
