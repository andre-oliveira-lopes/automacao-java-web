package br.com.automacao.utils; // Define onde essa classe está organizada dentro do projeto.

import org.openqa.selenium.By; // Permite usar a classe By, que serve para localizar elementos na página.
import org.openqa.selenium.WebDriver; // Classe principal do Selenium. Ela representa o navegador (Chrome)
import org.openqa.selenium.WebElement; // epresenta um elemento da página, como: Botão, Campo de texto, Link e Tabela.
import org.openqa.selenium.support.ui.ExpectedConditions; // Contém condições de espera, como: Esperar elemento aparecer e Esperar botão ficar clicável
import org.openqa.selenium.support.ui.WebDriverWait; // Classe usada para criar uma espera explícita. Ela faz o Selenium esperar um tempo até o elemento aparecer.
import java.time.Duration; // Usado para trabalhar com tempo, como milissegundos ou segundos.

// A classe identificarElementoSeletor é responsável por localizar elementos na página
public class IdentificarElementoSeletor {

    // Variável privada do tipo WebDriver para controlar o navegador
    private WebDriver navegador;

    // Construtor da classe que recebe o WebDriver
    public IdentificarElementoSeletor(WebDriver navegador) {
        // Atribui o navegador recebido para a variável da classe
        this.navegador = navegador;
    }

    // Método responsável por aguardar um elemento aparecer na tela
    public WebElement aguardarElemento(String seletor, long tempoLimite) {

        // Cria uma espera explícita com o tempo definido
        WebDriverWait espera = new WebDriverWait(this.navegador, Duration.ofMillis(tempoLimite));

        // Verifica se o seletor é XPath
        if (seletor.startsWith("//")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath(seletor)));
        } 
        // Verifica se é CSS
        else if (seletor.startsWith("css=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(seletor.substring(4))));
        } 
        // Verifica se é ID
        else if (seletor.startsWith("id=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.id(seletor.substring(3))));
        } 
        // Verifica se é NAME
        else if (seletor.startsWith("name=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.name(seletor.substring(5))));
        } 
        // Verifica se é LINK TEXT
        else if (seletor.startsWith("linktext=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.linkText(seletor.substring(9))));
        } 
        // Verifica se é PARTIAL LINK TEXT
        else if (seletor.startsWith("partiallinktext=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(seletor.substring(16))));
        } 
        // Verifica se é TAG NAME
        else if (seletor.startsWith("tagname=")) {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.tagName(seletor.substring(8))));
        } 
        // Caso não tenha prefixo, assume CSS padrão
        else {
            return espera.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(seletor)));
        }
    }
}
