# AutomacaoJavaWeb

Robô de automação web desenvolvido em Java com Selenium para coletar declarações do site Aos Fatos e salvar os dados em arquivos CSV para análise.

## Objetivo do projeto
Automatizar a navegação no site Aos Fatos para:

1. Acessar as páginas de declarações
2. Coletar os dados estruturados de cada declaração
3. Exportar os resultados para arquivos CSV

## Dados coletados
Para cada declaração, o robô extrai:

1 - Encontrar todos os elementos com classe fact.
2 - Para cada um: 

	| Nº | Campo                    | Pode extrair?  |
	| -- | ------------------------ | -------------- |
	| 1  | id da declaração         | ✅             |
	| 2  | data                     | ✅             |
	| 3  | link da declaração       | ✅             |
	| 4  | frase principal          | ✅             |
	| 5  | texto explicativo        | ✅             |
	| 6  | link “Leia mais”         | ✅             |
	| 7  | link da fonte            | ✅             |
	| 8  | link da origem           | ✅             |
	| 9  | quantidade de repetições | ✅             |
	| 10 | datas das repetições     | ✅             |
	| 11 | tema                     | ✅             |
	| 12 | tipo de origem           | ✅             |

3 - Se algum campo não existir, o robô continua a execução e deixa o campo vazio no CSV.

## Funcionamento do robô
Fluxo geral da automação

1. Abre a página de declarações.
2. Localiza todos os elementos com a classe fact.
3. Percorre cada declaração.
4. Extrai os dados disponíveis.
5. Salva os dados no arquivo CSV.
6. Repete o processo para as próximas páginas.

## Estrutura das classes
Classe principal: AutomacaoApplication
Responsável por:

1. Controlar o navegador
2. Navegar pelas páginas
3. Coletar os dados
4. Salvar no CSV

Classe utilitária: ElementUtil
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

## Função de cada tecnologia
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
1. Clonar o repositório: git clone <url-do-repositorio>
2. Entre na pasta do projeto: cd AutomacaoJavaWeb
3. Executar a classe de testes no terminal: "mvn test" 

## Estrutura do projeto
src
 ├── main
 │   └── java
 └── test
     └── java
         ├── AutomacaoApplication.java
         └── ElementUtil.java

## Estrutura de execução do projeto

INÍCIO
  ↓
Abrir navegador
  ↓
Acessar página do site
  ↓
Definir totalGeral = 0
  ↓
PARA cada página:
    ↓
    Coletar todas as declarações da página
    ↓
    Ignorar as 3 primeiras
    ↓
    Contar apenas as válidas (15)
    ↓
    Exibir total da página
    ↓
    Somar no totalGeral
    ↓
    Ir para próxima página
FIM DO LOOP
  ↓
Exibir TOTAL GERAL
  ↓
Salvar CSV
  ↓
Fechar navegador
  ↓
FIM

## explicação da estrutura do projeto
Atualmente, é normal e correto que o código do robô esteja localizado na pasta src/test do projeto. Isso acontece porque o robô está sendo executado como um teste automatizado, utilizando Java com Selenium dentro da estrutura padrão de projetos que seguem o modelo de testes (como JUnit ou TestNG).
Em projetos Java organizados dessa forma, o diretório src é dividido em duas partes principais: src/main, que contém o código principal da aplicação, e src/test, que é destinado aos códigos de teste, automações e validações. Como o robô está sendo executado como um teste, ele se encaixa naturalmente dentro da pasta src/test/java.
Essa organização é comum no mercado, principalmente para:
•	Testes automatizados de sistemas
•	Robôs de navegação com Selenium
•	Scripts de automação e coleta de dados
•	Testes de integração
Ou seja, no estágio atual do projeto, o robô funciona como uma automação de teste, e por isso faz sentido estar dentro de src/test.
O código só seria movido para src/main/java quando deixasse de ser um teste e passasse a funcionar como uma aplicação principal. Isso normalmente acontece quando o robô vira um programa executável, com classe principal (main), interface, menu ou integração com outros sistemas.
