package br.com.automacao;
/*
Inicia o projeto Spring: O arquivo AutomacaoApplication.java é o ponto de entrada do projeto Spring Boot. 
Ele contém a anotação @SpringBootApplication, que indica que esta é a classe principal da aplicação.
O método main() chama SpringApplication.run(), que inicia a aplicação Spring Boot.
Ela não executa o robô, ela só inicia o Spring. 
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomacaoApplication.class, args);
	}

}
