# AutomacaoJavaWeb

Projeto de automação web desenvolvido em Java utilizando Selenium.

## Esse tipo de página é ideal para automação de:

- Coleta de dados (web scraping)
- Testes automatizados
- Extração de textos e classificações

## Os objetivos mais comuns são:

1. Abrir a página.
2. Coletar as declarações exibidas.
3. Capturar: Texto da declaração, Classificação, Data e etc.
4. Salvar em: Lista, Arquivo, Banco de dados e Planilha.

## Objetivo Principal
Automatizar a navegação no site do AOS FATOS e baixar arquivos de declarações de Bolsonaro feitas a partir do dia de sua posse como presidente ao fim da sua posse.

## Resumo do funcionamento
O HTML está bem estruturado e facilita muito a extração. 
Cada declaração está dentro de: class="fact". Então a estratégia será.

Fluxo geral da "Classe principal: AutomacaoApplication"

1 - Encontrar todos os elementos com classe fact.
2 - Para cada um: 
	* Data
	* Frase principal
	* Texto explicativo
	* Link “LEIA MAIS”
	* Link “FONTE”
	* Link “ORIGEM”
	* Quantidade de repetições
	* Datas das repetições
	* Tema
	* Origem (tipo: live, entrevista, etc.)
3 - Se algum campo não existir, o código não quebra, apenas deixa o campo vazio no CSV. 

Fluxo geral da "Classe ElementUtil (utilitária)"
Essa classe serve para facilitar a busca de elementos na página.

1. Espera o elemento aparecer.
2. Identifica o tipo de seletor:
    XPath
    CSS
    ID
    Name
    LinkText
3. Retorna o elemento já pronto para uso.

## Tecnologias utilizadas
- Java
- Maven
- Selenium WebDriver (automação do navegador)
- JUnit 5 (testes automatizados)
- WebDriverManager (gerenciamento automático do driver do Chrome)
- Spring Boot 3.5.0 (estrutura base do projeto)

## O que cada uma faz
| Biblioteca       | Função                      | Comparação simples               |
| ---------------- | --------------------------- | -------------------------------- |
| Spring Boot      | Estrutura do projeto        | O esqueleto da casa              |
| JUnit            | Executa os testes           | O botão “executar”               |
| Selenium         | Controla o navegador        | O robô que usa o mouse e teclado |
| WebDriverManager | Baixa o driver do navegador | O instalador automático          |

## Dependências principais (pom.xml)
spring-boot-starter
spring-boot-starter-test
selenium-java
webdrivermanager

## Como executar
1. Clonar o repositório
2. Importar o projeto como Maven
3. Executar a classe de testes com "mvn test" no terminal

## Estrutura do projeto
