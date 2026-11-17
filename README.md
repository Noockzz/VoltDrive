# VoltDrive ⚡🚗

Aplicação desktop em **Java (JavaFX)** de catálogo de veículos elétricos, desenvolvida **em equipe** durante o curso no **SENAC** (2024).

## Funcionalidades

- Tela de login de usuário
- Controle de acesso com perfis de **usuário** e **administrador**
- Catálogo de veículos elétricos (BMW, Tesla, Ford, GWM) com imagem, descrição e preço
- Navegação entre telas
- Banco de dados MySQL

## Tecnologias

- Java + JavaFX (telas em FXML)
- Maven
- MySQL

## Estrutura

```
SQL/            → script do banco de dados (voltDrive.sql)
src/main/java   → controllers e classe principal da aplicação
src/main/resources → telas (.fxml) e imagens da interface
src/Imagens     → fotos dos veículos
src/documentos  → análise de requisitos e apresentação do projeto
```

## Como executar

1. Crie o banco rodando o script `SQL/voltDrive.sql` no MySQL
2. Abra o projeto em uma IDE com suporte a Maven (ex.: IntelliJ IDEA)
3. Execute a classe `HelloApplication`

## Minha contribuição

Participei do desenvolvimento de telas, como a de **login de usuário**, e do levantamento e cadastro dos **dados dos veículos** (modelos, descrições e preços).
