# API de Agendamento de Consultas Médicas

Esta aplicação é uma **API REST** para gerenciamento de médicos, pacientes e consultas médicas.

O objetivo do projeto é demonstrar uma arquitetura backend organizada, com separação clara de responsabilidades, aplicação de boas práticas e foco em **manutenibilidade**.

---

## 🚀 Funcionalidades

- Cadastro, atualização, listagem e inativação de médicos
- Agendamento de consultas médicas
- Cancelamento de consultas
- Validações de regras de negócio (horário de funcionamento, disponibilidade, etc.)
- Persistência de dados com banco relacional

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** – linguagem principal
- **Spring Boot 3** – framework base da aplicação
- **Maven** – gerenciamento de dependências e build
- **PostGre* – banco de dados relacional
- **Hibernate (JPA)** – ORM para persistência
- **Lombok** – redução de boilerplate

---

## 📌 Observações

- O projeto prioriza **clareza e simplicidade** em vez de seguir padrões de forma dogmática.
- Decisões de acoplamento foram feitas de forma consciente, considerando custo x benefício.
- A estrutura permite evolução gradual conforme o sistema cresce.
