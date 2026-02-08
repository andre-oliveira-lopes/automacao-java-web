package br.com.automacao.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ColetarDeclaracoesTest {

    @Test
    @DisplayName("Abrir página de declarações do Bolsonaro")
    public void abrirPaginaDeclaracoes() {

        // Configura automaticamente o driver do Chrome
        WebDriverManager.chromedriver().setup();

        // Abre o navegador
        WebDriver navegador = new ChromeDriver();

        // Acessa a página das declarações
        navegador.get("https://www.aosfatos.org/todas-as-declaracoes-de-bolsonaro/");

        // Aguarda 5 segundos para visualização
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Fecha o navegador
        navegador.quit();
    }
}
