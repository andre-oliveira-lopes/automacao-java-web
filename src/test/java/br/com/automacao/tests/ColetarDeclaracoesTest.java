package br.com.automacao.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ColetarDeclaracoesTest {

    @Test
    @DisplayName("Abrir página de declarações do Bolsonaro")
    public void abrirPaginaDeclaracoes() {

        // Força o encoding UTF-8
        System.setProperty("file.encoding", "UTF-8");

        // Configura automaticamente o driver do Chrome
        WebDriverManager.chromedriver().setup();

        // Abre o navegador
        WebDriver navegador = new ChromeDriver();

        // Acessa a página das declarações
        navegador.get("https://api.aosfatos.org/todas-as-declaracoes-de-bolsonaro/");

        // ================= ETAPA 1 =================
        // Espera inteligente até aparecer pelo menos uma declaração na página

        // Cria uma espera explícita de até 10 segundos
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
        // Aguarda até que pelo menos um elemento com a classe "fact" esteja presente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.fact")));
        // ================= ETAPA 2 =================
        // Coleta as declarações exibidas na página
        
        // Pega todas as declarações
        List<WebElement> declaracoes = navegador.findElements(By.cssSelector("div.fact"));

        // Exibir no console a quantidade de declarações coletadas
        System.out.println("\n||||||||||||||||||||||||||||||||||||||||");
        System.out.println("Total de declaracoes: " + declaracoes.size());
        System.out.println("\n");

        // for-each: usado para percorrer uma lista. Para cada declaração dentro da lista "List<WebElement>" de declarações, execute o código abaixo.
        for (WebElement declaracao : declaracoes) {

            // ================= ID =================
            // Aqui estamos pegando o valor do atributo "id"
            String id = declaracao.getDomAttribute("id");

            // ================= DATA =================
            // Nem toda declaração tem a data no mesmo lugar, então começamos com a data vazia
            String data = "";

            // Tentamos pegar a data
            try {
                // Procuramos o elemento que contém a data usando o seletor CSS "p.w600, pega ele e joga em data"
                data = declaracao.findElement(By.cssSelector("p.w600")).getText();
            } catch (Exception ignored) {} // Se não encontrar, ignora o erro e continua a execução

            // Exibir no console
            System.out.println("ID: " + id);
            System.out.println("Data: " + data);
            System.out.println("----------------------");
        }

        // Fecha o navegador
        navegador.quit();
    }
}
