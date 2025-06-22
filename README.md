# API REST - Sistema de Gestão de Produtos

## 📋 Descrição

API RESTful desenvolvida em Java com Spring Boot seguindo o padrão arquitetural MVC para gestão de produtos de e-commerce. Este projeto foi desenvolvido como parte do desafio final do Bootcamp de Arquiteto(a) de Software.

## 🏗️ Arquitetura

### Padrão MVC Implementado

- **Model**: Entidade `Produto` e repositórios JPA
- **View**: Representação JSON via DTOs (Request/Response)
- **Controller**: Endpoints REST para operações CRUD

### Camadas da Aplicação

1. **Controller Layer** - Gerenciamento de requisições HTTP
2. **Service Layer** - Lógica de negócio e orquestração
3. **Repository Layer** - Acesso e persistência de dados
4. **Model Layer** - Entidades de domínio

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web** - API REST
- **Spring Data JPA** - Persistência
- **Spring Validation** - Validação de dados
- **H2 Database** - Banco em memória
- **Maven** - Gerenciamento de dependências
- **JUnit 5** - Testes unitários
- **Mockito** - Mocks para testes

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/ecommerce/produtoapi/
│   │   ├── ProdutoApiApplication.java          # Classe principal
│   │   ├── controller/
│   │   │   └── ProdutoController.java          # Controlador REST
│   │   ├── service/
│   │   │   └── ProdutoService.java             # Lógica de negócio
│   │   ├── repository/
│   │   │   └── ProdutoRepository.java          # Acesso a dados
│   │   ├── model/
│   │   │   └── Produto.java                    # Entidade JPA
│   │   ├── dto/
│   │   │   ├── ProdutoRequestDTO.java          # DTO de entrada
│   │   │   └── ProdutoResponseDTO.java         # DTO de saída
│   │   └── exception/
│   │       ├── ProdutoNotFoundException.java   # Exceção customizada
│   │       └── GlobalExceptionHandler.java     # Tratamento global
│   └── resources/
│       ├── application.yml                     # Configurações
│       └── data.sql                           # Dados iniciais
└── test/
    └── java/com/ecommerce/produtoapi/
        └── service/
            └── ProdutoServiceTest.java         # Testes unitários
```

## 🔧 Configuração e Execução

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

### Executando a aplicação

1. **Clone o repositório**
```bash
git clone [URL_DO_REPOSITORIO]
cd produto-api
```

2. **Executando via Docker**
```bash
 docker-compose up --build
```

3. **Acesse a aplicação**
- API: http://localhost:8080/api/produtos
- Documentação Swagger: http://localhost:8080/swagger-ui.html

## 📚 Endpoints da API

### Operações CRUD Básicas

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| GET | `/api/produtos` | Listar todos os produtos | 200 |
| GET | `/api/produtos/{id}` | Buscar produto por ID | 200/404 |
| POST | `/api/produtos` | Criar novo produto | 201 |
| PUT | `/api/produtos/{id}` | Atualizar produto | 200/404 |
| DELETE | `/api/produtos/{id}` | Deletar produto | 204/404 |

### Operações Adicionais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/produtos/nome/{nome}` | Buscar por nome |
| GET | `/api/produtos/categoria/{categoria}` | Buscar por categoria |
| GET | `/api/produtos/disponiveis` | Listar produtos disponíveis |
| GET | `/api/produtos/contar` | Contar produtos |
| PATCH | `/api/produtos/{id}/inativar` | Inativar produto |

## Funcionalidades implementadas além do escopo inicial

- **Gestão de Clientes**: CRUD completo para clientes, incluindo campos de nome, email, telefone, CPF, status (ativo/inativo) e datas de criação/atualização.
- **Gestão de Pedidos**: CRUD completo para pedidos, com relacionamento a clientes e itens de pedido.
- **Itens do Pedido**: Cada pedido pode conter múltiplos itens, vinculados a produtos e com quantidade.
- **Status do Pedido**: Campo enumerado para status do pedido (PENDENTE, FATURADO, ENTREGUE), com valor padrão PENDENTE.
- **Validações Avançadas**: Uso de anotações de validação (javax.validation) para garantir integridade dos dados em todas as entidades.
- **Auditoria**: Campos automáticos de data de criação e atualização em todas as entidades principais.
- **Scripts SQL**: Arquivos `schema.sql` e `data.sql` atualizados para refletir todas as entidades, relacionamentos e dados de exemplo.
- **Testes Unitários**: Testes unitários completos para ProdutoService, ClienteService e PedidoService, cobrindo cenários de sucesso e exceção.
- **Endpoints REST**: Controllers REST para produtos, clientes e pedidos, seguindo boas práticas de arquitetura e DTOs.
- **Exceções customizadas**: Tratamento de exceções específicas para entidades não encontradas e validação de regras de negócio.

## 📝 Exemplos de Uso

### Criar Produto

```bash
POST /api/produtos
Content-Type: application/json

{
    "nome": "Smartphone iPhone 15",
    "descricao": "Smartphone Apple com 128GB de armazenamento",
    "preco": 4999.99,
    "categoria": "Eletrônicos",
    "estoque": 25
}
```

### Resposta

```json
{
    "id": 1,
    "nome": "Smartphone iPhone 15",
    "descricao": "Smartphone Apple com 128GB de armazenamento",
    "preco": 4999.99,
    "categoria": "Eletrônicos",
    "estoque": 25,
    "ativo": true,
    "dataCriacao": "2024-01-15T10:30:00",
    "dataAtualizacao": "2024-01-15T10:30:00"
}
```

## 🧪 Testes

### Executar testes
```bash
mvn test
```

### Cobertura de testes
- Testes unitários para camada de serviço
- Mocks para dependências externas
- Validação de cenários de sucesso e erro

## 🔒 Validações Implementadas

### Produto Entity
- **Nome**: obrigatório, 2-100 caracteres
- **Preço**: obrigatório, maior que zero
- **Categoria**: obrigatória, 2-50 caracteres
- **Estoque**: não pode ser negativo
- **Descrição**: máximo 500 caracteres (opcional)

## 🎯 Principais Funcionalidades

### ✅ Implementadas
- [x] Operações CRUD completas
- [x] Busca por nome e categoria
- [x] Contagem de produtos
- [x] Validação de dados
- [x] Tratamento de exceções
- [x] Soft delete (inativação)
- [x] Timestamps automáticos
- [x] Testes unitários
- [x] Documentação Swagger/OpenAPI
- [x] Deploy containerizado
- [x] Gestão de Clientes
- [x] Gestão de Pedidos
- [x] Itens do Pedido
- [x] Status do Pedido
- [x] Validações Avançadas
- [x] Auditoria
- [x] Scripts SQL atualizados
- [x] Exceções customizadas

### 🔄 Melhorias Futuras
- [ ] Autenticação e autorização
- [ ] Paginação e ordenação
- [ ] Cache com Redis
- [ ] Logs estruturados
- [ ] Métricas e monitoramento


## 🏆 Benefícios da Arquitetura

1. **Separação de Responsabilidades**: Cada camada tem função específica
2. **Testabilidade**: Componentes isolados facilitam testes
3. **Manutenibilidade**: Código organizado e estruturado
4. **Escalabilidade**: Arquitetura permite crescimento gradual
5. **Reutilização**: Serviços podem ser reutilizados

## 📧 Contato

Desenvolvido como parte do Bootcamp de Arquitetura de Software.

---

**Nota**: Este projeto demonstra a implementação prática dos conceitos de arquitetura de software, focando em boas práticas, design patterns e estruturação de código seguindo o padrão MVC.
