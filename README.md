# 🔥 Denuncie Chamas - Backend

API REST desenvolvida para o sistema **Denuncie Chamas**, responsável pelo gerenciamento de usuários, autenticação e registro de ocorrências de incêndios.

## 📌 Sobre o projeto

Este backend foi desenvolvido utilizando **Spring Boot**, com foco em boas práticas de arquitetura, segurança e organização do código.

A API é responsável por processar as requisições do frontend, aplicar regras de negócio e realizar a comunicação com o banco de dados.

---

## 🚀 Tecnologias utilizadas

- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- Supabase (PostgreSQL)
- Maven

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller** → Recebe requisições HTTP
- **Service** → Contém regras de negócio
- **Repository** → Acesso ao banco de dados
- **DTOs** → Transferência segura de dados entre camadas

---

## 🔐 Segurança

A aplicação foi estruturada com foco em segurança:

- Autenticação com **Spring Security**
- Proteção de rotas
- Controle de acesso
- Uso de **DTOs** para evitar exposição direta das entidades do banco

---

## ⚙️ Funcionalidades

- Cadastro de usuários
- Autenticação de usuários
- Registro de ocorrências
- Listagem de ocorrências
- Integração com frontend via API REST

---

## 🌐 Endpoints (exemplo)

```http
POST /auth/login
POST /auth/register

GET /ocorrencias
POST /ocorrencias
