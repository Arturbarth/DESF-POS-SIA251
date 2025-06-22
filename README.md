# API REST - Sistema de Gestão de Produtos, Clientes e Pedidos

## Descrição
API RESTful desenvolvida em Java com Spring Boot para gestão de produtos, clientes e pedidos, incluindo controle de estoque, status de pedidos e relacionamento entre entidades. O projeto segue o padrão arquitetural MVC e boas práticas de desenvolvimento.

## Funcionalidades Implementadas

- **Gestão de Produtos**: CRUD completo, busca por nome/categoria, inativação, contagem, validações e auditoria.
- **Gestão de Clientes**: CRUD completo, busca, inativação, validações, auditoria e campo CPF.
- **Gestão de Pedidos**: CRUD completo, relacionamento com clientes e itens, cálculo automático do valor total, auditoria.
- **Itens do Pedido**: Cada pedido pode conter múltiplos itens, vinculados a produtos e com quantidade.
- **Status do Pedido**: Campo enumerado (PENDENTE, FATURADO, ENTREGUE) com valor padrão PENDENTE.
- **Validações Avançadas**: Uso de anotações de validação para garantir integridade dos dados.
- **Auditoria**: Datas automáticas de criação e atualização em todas as entidades principais.
- **Scripts SQL**: Arquivos `schema.sql` e `data.sql` atualizados para refletir todas as entidades, relacionamentos e dados de exemplo.
- **Testes Unitários**: Testes para ProdutoService, ClienteService e PedidoService, cobrindo cenários de sucesso e exceção.
- **Tratamento de Exceções**: Exceções customizadas para entidades não encontradas e regras de negócio.

## Estrutura do Projeto

```
src/
  main/
    java/
      br/com/arturbarth/desfsia251/
        Desfsia251Application.java
        controller/
          ApplicationController.java
          ClienteController.java
          PedidoController.java
          ProdutoController.java
        dto/
          ClienteRequestDTO.java
          ClienteResponseDTO.java
          PedidoItemRequestDTO.java
          PedidoItemResponseDTO.java
          PedidoRequestDTO.java
          PedidoResponseDTO.java
          ProdutoRequestDTO.java
          ProdutoResponseDTO.java
        model/
          entity/
            Cliente.java
            Pedido.java
            PedidoItem.java
            Produto.java
          enums/
            Status.java
          exception/
            ClienteNotFoundException.java
            GlobalExceptionHandler.java
            PedidoItemNotFoundException.java
            PedidoNotFoundException.java
            ProdutoNotFoundException.java
          repository/
            ClienteRepository.java
            PedidoItemRepository.java
            PedidoRepository.java
            ProdutoRepository.java
          service/
            ClienteService.java
            ClienteServiceImpl.java
            PedidoService.java
            PedidoServiceImpl.java
            ProdutoService.java
            ProdutoServiceImpl.java
    resources/
      application.yml
      schema.sql
      data.sql
  test/
    java/
      br/com/arturbarth/desfsia251/
        service/
          ClienteServiceTest.java
          PedidoServiceTest.java
          ProdutoServiceTest.java
    resources/
      application-test.yml
```

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Hibernate
- Bean Validation (Jakarta Validation)
- Lombok
- PostgreSQL (ou H2 para testes)
- JUnit 5 e Mockito
- Docker (opcional)

## Como Executar

### Usando Docker (recomendado)

1. Certifique-se de ter Docker e Docker Compose instalados.
2. Execute o comando abaixo na raiz do projeto:
   ```bash
   docker-compose up --build
   ```
3. A aplicação estará disponível em `http://localhost:8080`.

### Executando localmente (sem Docker)

1. Configure o banco de dados em `src/main/resources/application.yml` (PostgreSQL recomendado ou H2 para testes).
2. Certifique-se de que o banco está rodando.
3. Execute o projeto com:
   ```bash
   mvn spring-boot:run
   ```
4. Os scripts `schema.sql` e `data.sql` serão executados automaticamente.
5. Acesse os endpoints REST para testar as funcionalidades.

## Endpoints Principais

- `/api/produtos` - CRUD de produtos
- `/api/clientes` - CRUD de clientes
- `/api/pedidos` - CRUD de pedidos

## Endpoints da Aplicação

### Produtos
- `GET    /api/produtos` — Lista todos os produtos
- `GET    /api/produtos/{id}` — Busca produto por ID
- `POST   /api/produtos` — Cria um novo produto
- `PUT    /api/produtos/{id}` — Atualiza um produto existente
- `DELETE /api/produtos/{id}` — Remove um produto
- `GET    /api/produtos/nome/{nome}` — Busca produtos por nome
- `GET    /api/produtos/categoria/{categoria}` — Busca produtos por categoria
- `GET    /api/produtos/disponiveis` — Lista produtos ativos
- `GET    /api/produtos/contar` — Conta o total de produtos
- `PATCH  /api/produtos/{id}/inativar` — Inativa um produto

### Clientes
- `GET    /api/clientes` — Lista todos os clientes
- `GET    /api/clientes/{id}` — Busca cliente por ID
- `POST   /api/clientes` — Cria um novo cliente
- `PUT    /api/clientes/{id}` — Atualiza um cliente existente
- `DELETE /api/clientes/{id}` — Remove um cliente
- `PATCH  /api/clientes/{id}/inativar` — Inativa um cliente
- `GET    /api/clientes/contar` — Conta o total de clientes
- `GET    /api/clientes/contar-ativos` — Conta clientes ativos

### Pedidos
- `GET    /api/pedidos` — Lista todos os pedidos
- `GET    /api/pedidos/{id}` — Busca pedido por ID
- `POST   /api/pedidos` — Cria um novo pedido
- `PUT    /api/pedidos/{id}` — Atualiza um pedido existente
- `DELETE /api/pedidos/{id}` — Remove um pedido
- `GET    /api/pedidos/contar` — Conta o total de pedidos

## Exemplos de Status do Pedido
- PENDENTE
- FATURADO
- ENTREGUE

## Testes
Os testes unitários estão em `src/test/java/br/com/arturbarth/desfsia251/service/`.
Execute com:
```bash
mvn test
```

## Observações
- O projeto está pronto para expansão, incluindo autenticação, paginação, filtros avançados e integração com outros sistemas.
- Para dúvidas ou sugestões, entre em contato!
