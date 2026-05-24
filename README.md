# CatalogoApp

## Descrição do Projeto
O CatalogoApp é uma aplicação robusta de gerenciamento de produtos e categorias, desenvolvida como material prático para as aulas na disciplina de "Desenvolvimento para Servidores II" no curso de Sistemas para Internet. O projeto foi construído com o objetivo principal de proporcionar aos alunos o aprendizado sobre o desenvolvimento de sistemas full-stack, focando em boas práticas de arquitetura, segurança e persistência de dados.

Através deste projeto, exploramos a transição de armazenamentos temporários em memória para soluções profissionais utilizando bancos de dados relacionais e controle de acesso baseado em perfis.

## Tecnologias Utilizadas
*   Java 21 e Spring Boot 3
*   Spring Data JPA para persistência de dados
*   PostgreSQL como banco de dados relacional
*   Spring Security para autenticação e autorização
*   Thymeleaf para renderização de telas dinâmicas
*   Bootstrap 5 para interface responsiva
*   BCrypt para criptografia de senhas

## Principais Funcionalidades
*   Gerenciamento completo (CRUD) de Produtos e Categorias.
*   Sistema de busca e filtros dinâmicos por nome, categoria e data de atualização.
*   Controle de acesso diferenciado:
    *   Administrador (ADMIN): Acesso total para gerenciar produtos, categorias e usuários.
    *   Usuário (USER): Acesso de consulta aos produtos cadastrados.
*   Persistência de usuários no banco de dados com senhas protegidas por hash.
*   Inicialização automática de dados (Data Seeder) para facilitar o primeiro acesso ao ambiente de desenvolvimento.

## Estrutura de Pastas e Padrões
O projeto segue o padrão de responsabilidades separadas para garantir a manutenibilidade:
*   Models: Representação das entidades do banco de dados.
*   Repositories: Interfaces para comunicação direta com o PostgreSQL.
*   Services: Camada de lógica de negócio e autenticação customizada.
*   Controllers: Gerenciamento de rotas e fluxo entre a View e a Model.
*   Config: Classes de configuração de segurança e inicialização do sistema.

## Como Executar
1. Certifique-se de ter o Java 21 e o PostgreSQL instalados.
2. Clone o repositório.
3. Ajuste as credenciais do banco de dados no arquivo application.properties.
4. Execute a aplicação via IDE (como IntelliJ) ou terminal usando `./mvnw spring-boot:run`.
5. Ao iniciar pela primeira vez, o sistema criará automaticamente um usuário administrador inicial (admin / admin123).

## Créditos
Projeto desenvolvido em contexto acadêmico na FATEC Jales, sob a orientação do Professor James Campos, visando a formação em desenvolvimento multiplataforma e engenharia de software.
