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

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.fail;

public class ColetarDeclaracoesTest {

    // Método para limpar texto antes de salvar no CSV
    private String limparParaCSV(String texto) {
        if (texto == null) return "";

        // remove quebras de linha
        texto = texto.replace("\n", " ")
                    .replace("\r", " ");

        // escapa aspas internas
        texto = texto.replace("\"", "\"\"");

        return texto;
    }

    @Test
    @DisplayName("Abrir página de declarações do Bolsonaro")
    public void abrirPaginaDeclaracoes() {

        // Configura automaticamente o driver do Chrome
        WebDriverManager.chromedriver().setup();

        // Abre o navegador
        WebDriver navegador = new ChromeDriver();

        // Coloque o código de automação dentro de um bloco try-finally para garantir que o navegador seja fechado mesmo se ocorrer um erro
        try {

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

        // Cria um arquivo CSV para salvar os dados coletados
        try (PrintWriter writer = new PrintWriter(
            new OutputStreamWriter(
                new FileOutputStream("declaracoes.csv"),
                StandardCharsets.UTF_8
            )
        )) {

            // Escreve o cabeçalho do CSV
            writer.println("id,data,link,frase,texto,leia_mais,fonte,origem,qtd_repeticoes,datas_repeticoes");


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

                // ================= LINK DA DECLARAÇÃO =================
                String linkDeclaracao = "";
                try {
                    linkDeclaracao = declaracao
                            .findElement(By.cssSelector("a.microlink"))
                            .getDomAttribute("href");
                } catch (Exception ignored) {}


                // ================= FRASE PRINCIPAL =================
                String frasePrincipal = "";
                try {
                    frasePrincipal = declaracao
                            .findElement(By.cssSelector("h4"))
                            .getText();
                } catch (Exception ignored) {}

                // ================= TEXTO EXPLICATIVO =================
                String textoExplicativo = "";
                try {
                    textoExplicativo = declaracao
                            .findElement(By.cssSelector("p.neuton"))
                            .getText();
                } catch (Exception ignored) {}

                // ================= LINKS (LEIA MAIS, FONTE, ORIGEM) =================
                String linkLeiaMais = "";
                String linkFonte = "";
                String linkOrigem = "";

                try {
                    List<WebElement> links = declaracao.findElements(By.cssSelector("a.btn"));

                    for (WebElement link : links) {
                        String textoLink = link.getText().toUpperCase();
                        String href = link.getDomAttribute("href");

                        if (textoLink.contains("LEIA MAIS")) {
                            linkLeiaMais = href;
                        } else if (textoLink.contains("FONTE")) {
                            linkFonte = href;
                        } else if (textoLink.contains("ORIGEM")) {
                            linkOrigem = href;
                        }
                    }
                } catch (Exception ignored) {}

                // ================= QUANTIDADE DE REPETIÇÕES =================
                String qtdRepeticoes = "";

                try {
                    qtdRepeticoes = declaracao
                            .findElement(By.cssSelector("span.repetitions"))
                            .getText();
                } catch (Exception ignored) {}

                // ================= DATAS DAS REPETIÇÕES =================
                String datasRepeticoes = "";

                try {
                    WebElement dateList = declaracao.findElement(By.cssSelector("div.date-list"));

                    List<WebElement> anos = dateList.findElements(By.cssSelector("span.year"));
                    List<WebElement> dias = dateList.findElements(By.cssSelector("span.days"));

                    StringBuilder builder = new StringBuilder();

                    for (int i = 0; i < anos.size(); i++) {
                        String ano = anos.get(i).getText().replace("Em ", "").replace(":", "").trim();
                        String diasTexto = dias.get(i).getText().trim();

                        if (builder.length() > 0) {
                            builder.append(" | ");
                        }

                        builder.append(ano).append(": ").append(diasTexto);
                    }

                    datasRepeticoes = builder.toString();

                } catch (Exception ignored) {}

                // Limpa os dados antes de salvar
                id = limparParaCSV(id);
                data = limparParaCSV(data);
                linkDeclaracao = limparParaCSV(linkDeclaracao);
                frasePrincipal = limparParaCSV(frasePrincipal);
                textoExplicativo = limparParaCSV(textoExplicativo);
                linkLeiaMais = limparParaCSV(linkLeiaMais);
                linkFonte = limparParaCSV(linkFonte);
                linkOrigem = limparParaCSV(linkOrigem);
                qtdRepeticoes = limparParaCSV(qtdRepeticoes);
                datasRepeticoes = limparParaCSV(datasRepeticoes);


                // Salva os dados no arquivo CSV, separando por vírgula
                writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                        id,
                        data,
                        linkDeclaracao,
                        frasePrincipal,
                        textoExplicativo,
                        linkLeiaMais,
                        linkFonte,
                        linkOrigem,
                        qtdRepeticoes,
                        datasRepeticoes));
                
                // Exibir no console
                System.out.println("ID: " + id);
                System.out.println("Data: " + data);
                System.out.println("Link: " + linkDeclaracao);
                System.out.println("Frase: " + frasePrincipal);
                System.out.println("Texto: " + textoExplicativo);
                System.out.println("Leia Mais: " + linkLeiaMais);
                System.out.println("Fonte: " + linkFonte);
                System.out.println("Origem: " + linkOrigem);
                System.out.println("Repetições: " + qtdRepeticoes);
                System.out.println("Datas das repetições: " + datasRepeticoes);
                System.out.println("----------------------");
            }
        } catch (FileNotFoundException e) {
            fail("Não foi possível criar o arquivo: " + e.getMessage());
        }
        
        // Exibir mensagem de sucesso no console
        System.out.println("Dados coletados e salvos com sucesso!");

        // Ao finalizar as ações, o navegador será fechado no bloco finally
        } finally {
            // Fecha o navegador
            navegador.quit();
        }
    }
}
