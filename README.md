# 📋 Sistema de Cadastro de Usuário - Atividades Práticas 3 e 4

> **Disciplina:** Framework para desenvolvimento de software  
> **Instituição:** Unopar  
> **Atividades:** Prática 3 (Spring MVC) e Prática 4 (HTML5/JavaScript com ViaCEP)
> **Aluno:** Crisler Wintler
---

## 📑 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Atividades Práticas](#atividades-práticas)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Executar](#como-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [API REST (Spring MVC)](#api-rest-spring-mvc)
- [Frontend (HTML5/JavaScript)](#frontend-html5javascript)
- [Banco de Dados](#banco-de-dados)
- [Validações Implementadas](#validações-implementadas)
- [Testes Realizados](#testes-realizados)
- [Troubleshooting](#troubleshooting)
- [Referências](#referências)

---

## 🎯 Sobre o Projeto

Este projeto implementa um **Sistema Completo de Cadastro de Usuário** que integra os conhecimentos adquiridos nas Atividades Práticas 3 e 4 da disciplina "Framework para desenvolvimento de software".

### Objetivos Alcançados

✅ **Atividade Prática 3 (Spring MVC):**
- Aplicar conceitos do framework Spring Web MVC
- Construir formulário estilizado com Bootstrap 5
- Implementar validação de campos
- Integrar com banco de dados

✅ **Atividade Prática 4 (HTML5/JavaScript):**
- Implementar formulário em HTML5 com JavaScript
- Consumir API ViaCEP para preenchimento automático
- Validar campos com jQuery
- Estilizar com Bootstrap 5

### Inovação: Integração Backend + Frontend

Além das atividades solicitadas, foi implementada uma **integração completa** entre:
- Frontend (HTML5/JavaScript) - Atividade Prática 4
- Backend (Spring MVC) - Atividade Prática 3
- Banco de dados H2 para persistência

Isso permite que o formulário do frontend envie dados para a API REST do backend, realizando uma integração prática e realista de uma aplicação web moderna.

---

## 📚 Atividades Práticas

### Atividade Prática 3: Spring Web MVC

**Objetivo:** Implementar um formulário de cadastro utilizando Spring MVC e Bootstrap 5.

**Requisitos:**
- ✅ Criar projeto Java Web com Spring Web MVC
- ✅ Configurar servidor Tomcat 8.x
- ✅ Integrar Bootstrap 5 via CDN
- ✅ Criar formulário com 11 campos (nome, sobrenome, email, senha, CEP, rua, número, complemento, bairro, cidade, estado)
- ✅ Estilizar completamente com Bootstrap 5
- ✅ Implementar validação de dados

**Resultado Entregue:**
- API REST com endpoints para CRUD de usuários
- Validação de dados com Bean Validation
- Persistência em banco de dados H2
- Logging com SLF4J
- Tratamento robusto de erros

### Atividade Prática 4: HTML5/JavaScript com ViaCEP

**Objetivo:** Implementar formulário de cadastro em HTML5/JavaScript com consumo da API ViaCEP.

**Requisitos:**
- ✅ Criar formulário em HTML5
- ✅ Implementar validação com jQuery
- ✅ Consumir API ViaCEP para preenchimento automático
- ✅ Validar email com jQuery
- ✅ Estilizar com Bootstrap 5
- ✅ Implementar toggle de visibilidade de senha

**Resultado Entregue:**
- Formulário responsivo e acessível
- Validação em tempo real com feedback visual
- Integração com API ViaCEP funcionando
- Consumo de API Spring Boot para persistência
- Design minimalista corporativo

---

## 🏗️ Arquitetura

### Arquitetura em Camadas (Spring MVC)

```
┌─────────────────────────────────────────────────────┐
│         Frontend (HTML5/CSS/JavaScript)             │
│  - Formulário de Cadastro (Atividade Prática 4)     │
│  - Validação com jQuery                             │
│  - Consumo de APIs (ViaCEP + Spring Boot)           │
└────────────────┬────────────────────────────────────┘
                 │ HTTP/JSON
┌────────────────▼────────────────────────────────────┐
│      Controller REST (Spring MVC)                   │
│  - UsuarioController (Atividade Prática 3)          │
│  - Endpoints: POST, GET, DELETE                     │
│  - Mapeamento de rotas com @RequestMapping          │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│      Service (Lógica de Negócio)                    │
│  - UsuarioService                                   │
│  - Validações de negócio                            │
│  - Orquestração de operações                        │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│      Repository (Spring Data JPA)                   │
│  - UsuarioRepository                                │
│  - Consultas ao banco de dados                      │
│  - Operações CRUD                                   │
└────────────────┬────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────┐
│      Banco de Dados (H2)                            │
│  - Tabela: usuarios                                 │
│  - Persistência de dados                            │
└─────────────────────────────────────────────────────┘
```

### Fluxo de Requisição

```
1. Usuário preenche formulário (Frontend - AP4)
   ↓
2. Frontend valida dados (jQuery)
   ↓
3. Frontend busca CEP na API ViaCEP
   ↓
4. ViaCEP retorna dados de endereço
   ↓
5. Frontend envia POST para /api/usuarios (Backend - AP3)
   ↓
6. Controller (Spring MVC) recebe requisição
   ↓
7. Service valida dados (Bean Validation)
   ↓
8. Repository salva no banco de dados H2
   ↓
9. Banco de dados retorna usuário salvo
   ↓
10. Controller retorna JSON com ID
    ↓
11. Frontend exibe mensagem de sucesso
```

---

## 🛠️ Tecnologias Utilizadas

### Backend (Atividade Prática 3)

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| Java | 11+ | Linguagem de programação |
| Spring Boot | 3.x | Framework principal |
| Spring Web MVC | 6.x | Para endpoints REST |
| Spring Data JPA | 3.x | Acesso a dados |
| Hibernate | 6.x | ORM (Object-Relational Mapping) |
| H2 Database | 2.x | Banco de dados embutido |
| Lombok | 1.18.x | Redução de boilerplate |
| Bean Validation | 3.x | Validação de dados (Jakarta) |
| SLF4J | 2.x | Logging |
| Tomcat | 10.x | Servidor embarcado |

### Frontend (Atividade Prática 4)

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| HTML5 | - | Markup semântico |
| CSS3 | - | Estilos e animações |
| JavaScript | ES6+ | Lógica e validações |
| Bootstrap | 5.x | Framework CSS responsivo |
| jQuery | 3.x | Manipulação DOM |
| jQuery Validation | 1.19.x | Validação de formulários |
| Font Awesome | 6.x | Ícones |
| Axios | 1.x | Requisições HTTP |

### APIs Externas Consumidas

| API | Descrição | Uso |
|-----|-----------|-----|
| ViaCEP | API de endereços brasileira | Preenchimento automático de endereço pelo CEP |
| Spring Boot REST | API própria | Persistência de dados de cadastro |

---

## 📋 Pré-requisitos

### Para Backend (Atividade Prática 3)

- **Java 11 ou superior**
  ```bash
  java -version
  ```

- **Maven 3.6 ou superior**
  ```bash
  mvn -version
  ```

- **Tomcat 8.x** (opcional, Spring Boot inclui Tomcat embarcado)

### Para Frontend (Atividade Prática 4)

- **Navegador moderno** (Chrome, Firefox, Safari, Edge)
- **Node.js** (opcional, para desenvolvimento)
- **NetBeans IDE** (conforme recomendado na disciplina)

---

## 📦 Instalação e Configuração

### 1. Clonar o Repositório

```bash
git clone <url-do-repositorio>
cd cadastro-usuario
```

### 2. Estrutura de Diretórios

```
cadastro-usuario/
├── cadastro-usuario-api/          # Backend (Atividade Prática 3)
│   ├── src/
│   │   └── main/java/com/cadastro/
│   │       ├── controller/        # Spring MVC Controllers
│   │       ├── service/           # Lógica de negócio
│   │       ├── repository/        # Spring Data JPA
│   │       ├── entity/            # Modelos JPA
│   │       └── dto/               # Data Transfer Objects
│   ├── pom.xml                    # Dependências Maven
│   └── target/
│
├── cadastro-usuario-frontend/     # Frontend (Atividade Prática 4)
│   ├── client/
│   │   ├── index.html             # Formulário HTML5
│   │   ├── css/style.css          # Estilos CSS3
│   │   ├── js/controller.js       # Lógica JavaScript
│   │   └── public/images/         # Assets
│   └── package.json
│
└── README.md                      # Este arquivo
```

### 3. Instalar Dependências do Backend

```bash
cd cadastro-usuario-api
mvn clean install
```

### 4. Compilar o Projeto

```bash
mvn clean package
```

---

## 🚀 Como Executar

### 🔹 PASSO 1: Executar o Backend (API Spring Boot)

#### 1.1 Verificar Pré-requisitos

Certifique-se de que Java e Maven estão instalados:

```bash
java -version    # Deve exibir Java 11 ou superior
mvn -version     # Deve exibir Maven 3.6 ou superior
```

**Não tem instalado?**
- **Java 11:** https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- **Maven:** https://maven.apache.org/download.cgi

#### 1.2 Navegar até a pasta da API

```bash
cd C:\Documents\cadusuario\api
```

#### 1.3 Compilar o projeto (primeira vez)

```bash
mvn clean install
```
⏱️ **Aguarde:** Este processo pode levar alguns minutos na primeira execução

#### 1.4 Executar a aplicação

**Opção A - Com Maven (Recomendado):**
```bash
mvn spring-boot:run
```

**Opção B - Executar JAR compilado:**
```bash
java -jar target/cadastro-usuario-api-1.0.0.jar
```

**Opção C - Com Tomcat Externo:**
1. Copiar arquivo WAR para pasta `webapps` do Tomcat
2. Iniciar Tomcat
3. Acessar em `http://localhost:8080/cadastro-usuario-api`

#### 1.5 Aguardar a mensagem de sucesso

```
Started CadastroUsuarioApiApplication in X.XXX seconds
```

#### 1.6 Verificar se o Backend está funcionando

**No navegador ou terminal:**
```bash
curl http://localhost:8080/api/usuarios/health
```

**Resposta esperada:**
```json
{
  "status": "UP",
  "message": "API está funcionando corretamente"
}
```

✅ **Backend está rodando em:** `http://localhost:8080/api`

**⚠️ IMPORTANTE:** Deixe este terminal aberto! Se fechar, o backend para de funcionar.

---

### 🔹 PASSO 2: Executar o Frontend

Agora você precisa abrir o frontend em um navegador. Há várias formas:

#### **Opção A - Abrir Diretamente no Navegador (Mais Simples)**

1. Navegue até a pasta:
   ```
   C:\Documents\cadusuario\frontend
   ```

2. Clique duas vezes no arquivo `index.html`

3. O formulário será aberto no navegador padrão

**⚠️ LIMITAÇÃO:** Pode ter problemas com CORS dependendo do navegador.

---

#### **Opção B - Usando Python (Recomendado se tiver Python instalado)**

1. Abra um **NOVO terminal** (não feche o do backend!)

2. Navegue até a pasta frontend:
   ```bash
   cd C:\Documents\cadusuario\frontend
   ```

3. Execute o servidor HTTP:
   
   **Python 3:**
   ```bash
   python -m http.server 3000
   ```
   
   **Python 2:**
   ```bash
   python -m SimpleHTTPServer 3000
   ```

4. Acesse no navegador:
   ```
   http://localhost:3000
   ```

---

#### **Opção C - Usando Node.js/NPM (se tiver Node instalado)**

1. Instale o servidor http globalmente (apenas uma vez):
   ```bash
   npm install -g http-server
   ```

2. Navegue até a pasta frontend:
   ```bash
   cd C:\Documents\cadusuario\frontend
   ```

3. Execute o servidor:
   ```bash
   http-server -p 3000
   ```

4. Acesse no navegador:
   ```
   http://localhost:3000
   ```

---

#### **Opção D - Usando VS Code com Live Server**

1. Abra o **VS Code**

2. Instale a extensão **"Live Server"** (se ainda não tiver)

3. Abra a pasta `frontend` no VS Code

4. Clique com botão direito em `index.html`

5. Selecione: **"Open with Live Server"**

6. Abre automaticamente em: `http://127.0.0.1:5500`

---

### 🧪 PASSO 3: Testar o Sistema

#### ✅ Teste 1: Verificar Backend

Acesse no navegador:
```
http://localhost:8080/api/usuarios/health
```

**Deve retornar:**
```json
{
  "status": "UP",
  "message": "API está funcionando corretamente"
}
```

---

#### ✅ Teste 2: Testar API ViaCEP

1. Abra o arquivo de teste (opcional):
   ```
   C:\Documents\cadusuario\frontend\testar-cep.html
   ```

2. Digite um CEP válido: `01310-100`

3. Clique em "Buscar CEP"

4. **Resultado esperado:**
   ```
   ✅ CEP Encontrado!
   CEP: 01310-100
   Logradouro: Avenida Paulista
   Bairro: Bela Vista
   Cidade: São Paulo
   Estado: SP
   ```

---

#### ✅ Teste 3: Cadastrar um Usuário

1. Acesse o formulário frontend

2. Preencha os campos:
   - **Nome:** `João`
   - **Sobrenome:** `Silva`
   - **Email:** `joao.silva@email.com`
   - **Senha:** `senha123`
   - **CEP:** `01310-100` → Clique em "🔍 Buscar"
   - **Número:** `1000`
   - Os campos Rua, Bairro, Cidade e Estado serão preenchidos automaticamente

3. Clique em **"Cadastrar"**

4. **Resultado esperado:**
   - Mensagem verde: "✅ Cadastro realizado com sucesso! ID: 1"
   - Formulário limpa automaticamente após 2 segundos

---

#### ✅ CEPs para Teste

| CEP        | Localização                  |
|------------|------------------------------|
| 01310-100  | Av. Paulista, São Paulo/SP   |
| 20040-020  | Centro, Rio de Janeiro/RJ    |
| 30130-010  | Centro, Belo Horizonte/MG    |
| 40020-000  | Centro, Salvador/BA          |
| 80010-000  | Centro, Curitiba/PR          |
| 90010-000  | Centro, Porto Alegre/RS      |

---

### 🗄️ PASSO 4: Acessar o Banco de Dados H2 (Opcional)

O backend usa banco de dados H2 em memória. Para visualizar os dados:

1. Com o backend rodando, acesse no navegador:
   ```
   http://localhost:8080/api/h2-console
   ```

2. Preencha as configurações de conexão:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User Name:** `sa`
   - **Password:** (deixe vazio)

3. Clique em **"Connect"**

4. Execute uma query SQL para ver os usuários cadastrados:
   ```sql
   SELECT * FROM usuarios;
   ```

---

### 🛑 Como Parar o Projeto

#### Parar o Backend:
1. Vá até o terminal onde o backend está rodando
2. Pressione: **`Ctrl + C`**

#### Parar o Frontend:
- **Se abriu direto no navegador:** apenas feche a aba
- **Se usou servidor local (Python/Node):** vá até o terminal e pressione **`Ctrl + C`**

---

### ❗ Problemas Comuns e Soluções

#### 1. **Erro: "java: command not found"**

**Causa:** Java não está instalado ou não está no PATH

**Solução:**
- Instale o JDK 11: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- Configure a variável de ambiente `JAVA_HOME`

---

#### 2. **Erro: "mvn: command not found"**

**Causa:** Maven não está instalado ou não está no PATH

**Solução:**
- Instale o Maven: https://maven.apache.org/download.cgi
- Adicione Maven ao PATH do sistema

---

#### 3. **Erro: "Porta 8080 já está em uso"**

**Causa:** Outra aplicação está usando a porta 8080

**Solução A - Mudar a porta do backend:**

Edite o arquivo `api/src/main/resources/application.properties`:
```properties
server.port=8081
```

E também atualize o frontend em `frontend/client/controller.js`:
```javascript
const API_BACKEND = "http://localhost:8081/api";
```

**Solução B - Matar o processo na porta 8080:**

**Windows:**
```cmd
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

**Linux/Mac:**
```bash
lsof -i :8080
kill -9 <PID>
```

---

#### 4. **Erro: "CEP não busca endereço"**

**Verificações:**
- Abra o Console do navegador (F12) para ver erros
- Verifique se o arquivo está sendo carregado: `client/controller.js`
- Verifique sua conexão com internet (API ViaCEP é externa)
- Teste a API diretamente: https://viacep.com.br/ws/01310100/json/

---

#### 5. **Erro: "Formulário não valida ao salvar"**

**Verificações:**
- Abra o Console do navegador (F12) para ver erros JavaScript
- Verifique se o jQuery está carregando
- Verifique se o jQuery Validation está carregando
- Confirme que o caminho do script está correto: `client/controller.js`

---

#### 6. **Erro CORS no frontend**

**Causa:** Navegador bloqueia requisições de `file://` para `http://`

**Solução:** Use um servidor local (Python/Node.js/Live Server) em vez de abrir o HTML diretamente

---

#### 7. **Backend não responde às requisições**

**Verificações:**
1. Confirme que o backend está rodando:
   ```bash
   curl http://localhost:8080/api/usuarios/health
   ```

2. Verifique os logs no terminal do backend

3. Confirme que não há erros de compilação

---

### 📊 Endpoints da API Disponíveis

| Método | Endpoint                        | Descrição                     |
|--------|---------------------------------|-------------------------------|
| POST   | `/api/usuarios`                | Cadastrar novo usuário        |
| GET    | `/api/usuarios/{id}`           | Buscar usuário por ID         |
| GET    | `/api/usuarios/email/{email}`  | Buscar usuário por email      |
| GET    | `/api/usuarios`                | Listar todos os usuários      |
| GET    | `/api/usuarios/stats/total`    | Total de usuários cadastrados |
| DELETE | `/api/usuarios/{id}`           | Deletar usuário               |
| GET    | `/api/usuarios/health`         | Health check da API           |

---

### 🎯 Configurações Importantes

#### Backend (`application.properties`):
```properties
server.port=8080                          # Porta do servidor
server.servlet.context-path=/api          # Prefixo das URLs
spring.datasource.url=jdbc:h2:mem:testdb  # Banco H2 em memória
spring.h2.console.enabled=true            # Console H2 habilitado
```

#### Frontend (`controller.js`):
```javascript
const API_BACKEND = "http://localhost:8080/api";  # URL da API Spring Boot
const API_VIACEP = "https://viacep.com.br/ws";    # URL da API ViaCEP
const TIMEOUT_API = 5000;                         # Timeout de 5 segundos
```

---

## 📁 Estrutura do Projeto

### Backend - Atividade Prática 3

```
cadastro-usuario-api/src/main/java/com/cadastro/
│
├── CadastroUsuarioApiApplication.java
│   └── Classe principal com @SpringBootApplication
│
├── controller/
│   └── UsuarioController.java
│       ├── @RestController
│       ├── @RequestMapping("/usuarios")
│       ├── POST /usuarios - Cadastrar novo usuário
│       ├── GET /usuarios - Listar todos
│       ├── GET /usuarios/{id} - Buscar por ID
│       ├── GET /usuarios/email/{email} - Buscar por email
│       ├── GET /usuarios/stats/total - Total de usuários
│       ├── DELETE /usuarios/{id} - Deletar
│       └── GET /usuarios/health - Health check
│
├── service/
│   └── UsuarioService.java
│       ├── @Service
│       ├── cadastrarUsuario()
│       ├── buscarUsuarioPorId()
│       ├── buscarUsuarioPorEmail()
│       ├── listarTodosUsuarios()
│       ├── contarUsuarios()
│       └── deletarUsuario()
│
├── repository/
│   └── UsuarioRepository.java
│       ├── @Repository
│       ├── extends JpaRepository<Usuario, Long>
│       ├── findByEmail()
│       └── existsByEmail()
│
├── entity/
│   └── Usuario.java
│       ├── @Entity
│       ├── @Table(name = "usuarios")
│       ├── @Id @GeneratedValue
│       └── Campos mapeados para tabela
│
└── dto/
    ├── CadastroUsuarioDTO.java
    │   ├── DTO para requisição
    │   └── Validações com @NotBlank, @Email, @Pattern
    │
    └── UsuarioResponseDTO.java
        ├── DTO para resposta
        └── Conversão de Entity para DTO
```

### Frontend - Atividade Prática 4

```
cadastro-usuario-frontend/client/
│
├── index.html
│   ├── <!DOCTYPE html>
│   ├── <head>
│   │   ├── Bootstrap 5 CDN
│   │   ├── Font Awesome CDN
│   │   ├── jQuery CDN
│   │   ├── jQuery Validation CDN
│   │   └── Arquivo CSS customizado
│   │
│   └── <body>
│       ├── Seção: Dados Pessoais
│       │   ├── Nome (obrigatório)
│       │   └── Sobrenome (obrigatório)
│       │
│       ├── Seção: Contato
│       │   └── Email (obrigatório, validação)
│       │
│       ├── Seção: Segurança
│       │   ├── Senha (obrigatória)
│       │   └── Toggle de visibilidade
│       │
│       ├── Seção: Endereço
│       │   ├── CEP (obrigatório, busca ViaCEP)
│       │   ├── Rua (preenchida automaticamente)
│       │   ├── Número (obrigatório)
│       │   ├── Complemento (opcional)
│       │   ├── Bairro (preenchido automaticamente)
│       │   ├── Cidade (preenchida automaticamente)
│       │   └── Estado (preenchido automaticamente)
│       │
│       └── Botões
│           ├── Limpar
│           └── Cadastrar
│
├── css/
│   └── style.css
│       ├── Variáveis CSS
│       ├── Animações (fade-in, slide-in)
│       ├── Estados (normal, foco, erro, sucesso)
│       ├── Responsividade (mobile, tablet, desktop)
│       └── Modo escuro (prefers-color-scheme)
│
└── js/
    └── controller.js
        ├── Configuração da API ViaCEP
        ├── Configuração da API Spring Boot
        ├── Validação com jQuery Validation Plugin
        ├── Toggle de senha
        ├── Formatação de CEP
        ├── Busca na API ViaCEP
        ├── Validação de email em tempo real
        ├── Validação de senha em tempo real
        ├── Envio para API Spring Boot
        └── Tratamento de erros
```

---

## ✨ Funcionalidades Implementadas

### Atividade Prática 3: Backend (Spring MVC)

✅ **Arquitetura MVC Completa**
- Controller para roteamento
- Service para lógica de negócio
- Repository para acesso a dados
- Entity para mapeamento ORM

✅ **API REST**
- 7 endpoints funcionais
- Validação de dados com Bean Validation
- Tratamento robusto de erros
- Respostas em JSON

✅ **Persistência de Dados**
- Banco de dados H2 embutido
- Tabela `usuarios` com 13 campos
- Relacionamentos e constraints

✅ **Logging e Monitoramento**
- SLF4J para logging
- Health check endpoint
- Rastreamento de operações

### Atividade Prática 4: Frontend (HTML5/JavaScript)

✅ **Formulário Responsivo**
- 11 campos de entrada
- Design mobile-first
- Acessibilidade garantida

✅ **Validação Completa**
- Validação em tempo real
- Feedback visual imediato
- Mensagens de erro personalizadas

✅ **Consumo de APIs**
- Integração com ViaCEP
- Preenchimento automático de endereço
- Integração com API Spring Boot

✅ **Interatividade**
- Toggle de visibilidade de senha
- Formatação automática de CEP
- Animações suaves

---

## 🔌 API REST (Spring MVC)

### Base URL

```
http://localhost:8080/api
```

### Endpoints

#### 1. Cadastrar Novo Usuário

**Requisição:**
```http
POST /usuarios
Content-Type: application/json

{
  "nome": "João",
  "sobrenome": "Silva",
  "email": "joao.silva@example.com",
  "senha": "senha123",
  "cep": "01310-100",
  "rua": "Avenida Paulista",
  "numero": "1000",
  "complemento": "Apto 101",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "estado": "SP"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "João",
  "sobrenome": "Silva",
  "email": "joao.silva@example.com",
  "cep": "01310-100",
  "rua": "Avenida Paulista",
  "numero": "1000",
  "complemento": "Apto 101",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "estado": "SP",
  "dataCriacao": "2025-12-04T07:27:59.014727"
}
```

#### 2. Listar Todos os Usuários

**Requisição:**
```http
GET /usuarios
```

**Resposta (200 OK):**
```json
{
  "total": 2,
  "usuarios": [
    {
      "id": 1,
      "nome": "João",
      "sobrenome": "Silva",
      ...
    }
  ]
}
```

#### 3. Buscar Usuário por ID

**Requisição:**
```http
GET /usuarios/1
```

#### 4. Buscar Usuário por Email

**Requisição:**
```http
GET /usuarios/email/joao.silva@example.com
```

#### 5. Obter Total de Usuários

**Requisição:**
```http
GET /usuarios/stats/total
```

**Resposta:**
```json
{
  "total": 2
}
```

#### 6. Deletar Usuário

**Requisição:**
```http
DELETE /usuarios/1
```

#### 7. Health Check

**Requisição:**
```http
GET /usuarios/health
```

---

## 🎨 Frontend (HTML5/JavaScript)

### Campos do Formulário

#### Seção: Dados Pessoais
- **Nome** - Obrigatório, 3-100 caracteres, apenas letras
- **Sobrenome** - Obrigatório, 3-100 caracteres, apenas letras

#### Seção: Contato
- **Email** - Obrigatório, formato válido de email

#### Seção: Segurança
- **Senha** - Obrigatória, mínimo 6 caracteres
- **Toggle de Senha** - Mostrar/ocultar senha

#### Seção: Endereço
- **CEP** - Obrigatório, formato 00000-000
- **Rua** - Preenchida automaticamente pela ViaCEP
- **Número** - Obrigatório, apenas dígitos
- **Complemento** - Opcional
- **Bairro** - Preenchido automaticamente pela ViaCEP
- **Cidade** - Preenchida automaticamente pela ViaCEP
- **Estado** - Preenchido automaticamente pela ViaCEP

### Funcionalidades

✅ **Validação com jQuery Validation Plugin**
- Validação em tempo real
- Mensagens de erro personalizadas
- Feedback visual com cores

✅ **Consumo da API ViaCEP**
- Busca automática de endereço pelo CEP
- Preenchimento automático de rua, bairro, cidade e estado
- Tratamento de erros e CEP inválido

✅ **Consumo da API Spring Boot**
- Envio de dados para persistência
- Feedback de sucesso/erro
- Reset automático do formulário

✅ **Design Responsivo**
- Mobile (< 768px)
- Tablet (768px - 1023px)
- Desktop (1024px+)

---

## 🗄️ Banco de Dados

### Banco de Dados: H2

**Tipo:** Embutido (In-Memory)
**Arquivo:** `mem:testdb`
**Console:** `http://localhost:8080/h2-console`

### Tabela: usuarios

```sql
CREATE TABLE usuarios (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  sobrenome VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  cep VARCHAR(9) NOT NULL,
  rua VARCHAR(255) NOT NULL,
  numero VARCHAR(10) NOT NULL,
  complemento VARCHAR(255),
  bairro VARCHAR(100) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

---

## ✅ Validações Implementadas

### Frontend (jQuery Validation)

| Campo | Validações |
|-------|-----------|
| Nome | Obrigatório, 3-100 caracteres, apenas letras |
| Sobrenome | Obrigatório, 3-100 caracteres, apenas letras |
| Email | Obrigatório, formato válido |
| Senha | Obrigatória, mínimo 6 caracteres |
| CEP | Obrigatório, formato 00000-000 |
| Rua | Obrigatória, 3+ caracteres |
| Número | Obrigatório, apenas dígitos |
| Complemento | Opcional |
| Bairro | Obrigatório, 2+ caracteres |
| Cidade | Obrigatória, 2+ caracteres |
| Estado | Obrigatório, 2 letras maiúsculas |

### Backend (Bean Validation)

Mesmas validações são aplicadas no servidor para garantir integridade dos dados.

---

## 🧪 Testes Realizados

### Teste 1: Preenchimento Automático com ViaCEP

```bash
# Inserir CEP 01310-100
# Resultado: Avenida Paulista, Bela Vista, São Paulo, SP
✅ PASSOU
```

### Teste 2: Cadastro de Usuário

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João",
    "sobrenome": "Silva",
    "email": "joao@example.com",
    "senha": "senha123",
    "cep": "01310-100",
    "rua": "Avenida Paulista",
    "numero": "1000",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "estado": "SP"
  }'
```

✅ **Resultado:** Usuário cadastrado com ID 1

### Teste 3: Listagem de Usuários

```bash
curl http://localhost:8080/api/usuarios
```

✅ **Resultado:** Retorna lista com 2 usuários

### Teste 4: Validação de Email Duplicado

```bash
# Tentar cadastrar com email já existente
✅ Resultado: Erro 400 "Email já cadastrado no sistema"
```

### Teste 5: Validação de Campos Vazios

```bash
# Enviar formulário vazio
✅ Resultado: Mensagens de erro para campos obrigatórios
```

### Teste 6: Toggle de Senha

```bash
# Clicar no ícone de olho
✅ Resultado: Senha fica visível/invisível
```

---

## 🔧 Troubleshooting

### Problema: Porta 8080 já está em uso

**Solução:**
```bash
lsof -i :8080
kill -9 <PID>
```

### Problema: Maven não encontrado

**Solução:**
```bash
sudo apt-get install maven
```

### Problema: Java não encontrado

**Solução:**
```bash
sudo apt-get install openjdk-11-jdk
```

### Problema: API não responde

```bash
curl http://localhost:8080/api/usuarios/health
```

### Problema: Frontend não consegue conectar à API

Verificar URL em `controller.js`:
```javascript
const API_BACKEND = 'http://localhost:8080/api'
```

---



## 📚 Referências

### Atividade Prática 3 (Spring MVC)

- [Spring Web MVC Documentation](https://spring.io/guides/gs/serving-web-content/)
- [Spring Boot Official Guide](https://spring.io/guides/gs/spring-boot/)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.0/)
- [NetBeans Spring Web Guide](https://netbeans.apache.org/kb/docs/web/quickstart-webapps-spring_pt_BR.html)

### Atividade Prática 4 (HTML5/JavaScript)

- [ViaCEP API Documentation](https://viacep.com.br/)
- [jQuery Validation Plugin](https://jqueryvalidation.org/)
- [Bootstrap 5 Components](https://getbootstrap.com/docs/5.0/components/)
- [MDN Web Docs - HTML5](https://developer.mozilla.org/en-US/docs/Web/HTML)
- [MDN Web Docs - JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript)

### Ferramentas

- [Apache Tomcat](https://tomcat.apache.org/)
- [NetBeans IDE](https://netbeans.apache.org/)
- [H2 Database](https://www.h2database.com/)
- [Maven](https://maven.apache.org/)

---

## 📊 Estatísticas do Projeto

| Métrica | Valor |
|---------|-------|
| Linhas de Código (Backend) | ~500 |
| Linhas de Código (Frontend) | ~400 |
| Endpoints da API | 7 |
| Campos do Formulário | 11 |
| Validações Implementadas | 20+ |
| Testes Realizados | 6+ |
| Atividades Práticas Integradas | 2 |

---

## 🎓 Conceitos Demonstrados

### Atividade Prática 3 (Spring MVC)

✅ Arquitetura em camadas (MVC)
✅ Spring Boot e Spring Web MVC
✅ Spring Data JPA e Hibernate
✅ Bean Validation
✅ RESTful API Design
✅ Logging com SLF4J
✅ Tratamento de exceções
✅ Banco de dados relacional

### Atividade Prática 4 (HTML5/JavaScript)

✅ HTML5 semântico
✅ CSS3 responsivo
✅ JavaScript ES6+
✅ jQuery e jQuery Validation
✅ Consumo de APIs REST
✅ Consumo de APIs externas (ViaCEP)
✅ Validação de formulários
✅ Design responsivo

### Integração Backend + Frontend

✅ Comunicação HTTP
✅ JSON como formato de troca
✅ CORS (Cross-Origin Resource Sharing)
✅ Tratamento de erros end-to-end
✅ Validação em múltiplas camadas

---




## 📝 Licença

Este projeto é licenciado sob a MIT License.

---

## 🙏 Agradecimentos

Agradecimentos aos professores e à instituição pelo conhecimento transmitido sobre frameworks de desenvolvimento de software, especialmente Spring MVC e HTML5/JavaScript.

---

**Obrigado por usar este projeto!** 🚀
