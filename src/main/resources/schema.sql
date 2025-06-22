-- Criação da tabela produtos
CREATE TABLE IF NOT EXISTS produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500),
    preco DECIMAL(10,2) NOT NULL CHECK (preco > 0),
    categoria VARCHAR(50) NOT NULL,
    estoque INTEGER NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Índices para performance
CREATE INDEX IF NOT EXISTS idx_produtos_nome ON produtos(nome);
CREATE INDEX IF NOT EXISTS idx_produtos_categoria ON produtos(categoria);
CREATE INDEX IF NOT EXISTS idx_produtos_ativo ON produtos(ativo);

-- Criação da tabela CLIENTE
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP
);

-- Criação da tabela PEDIDO
CREATE TABLE IF NOT EXISTS pedido (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL REFERENCES cliente(id),
    valor_total DECIMAL(12,2) NOT NULL DEFAULT 0
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    data_atualizacao TIMESTAMP
    status VARCHAR(30)
);

-- Criação da tabela PEDIDO_ITEM
CREATE TABLE IF NOT EXISTS pedido_item (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL REFERENCES pedido(id) ON DELETE CASCADE,
    produto_id BIGINT NOT NULL REFERENCES produtos(id),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0)
);

-- Índices para performance
CREATE INDEX IF NOT EXISTS idx_cliente_nome ON cliente(nome);
CREATE INDEX IF NOT EXISTS idx_cliente_ativo ON cliente(ativo);
CREATE INDEX IF NOT EXISTS idx_pedido_cliente ON pedido(cliente_id);
CREATE INDEX IF NOT EXISTS idx_pedido_item_pedido ON pedido_item(pedido_id);
CREATE INDEX IF NOT EXISTS idx_pedido_item_produto ON pedido_item(produto_id);
