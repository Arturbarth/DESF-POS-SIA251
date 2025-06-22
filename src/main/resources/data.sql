-- Inserir dados iniciais apenas se a tabela estiver vazia
INSERT INTO produtos (nome, descricao, preco, categoria, estoque, ativo)
SELECT * FROM (VALUES
    ('Smartphone Samsung Galaxy S23', 'Smartphone com tela de 6.1 polegadas, 128GB', 2499.99, 'Eletrônicos', 50, true),
    ('Notebook Dell Inspiron', 'Notebook com Intel Core i5, 8GB RAM, 256GB SSD', 2999.99, 'Informática', 25, true),
    ('Camiseta Nike Dri-FIT', 'Camiseta esportiva masculina, tecnologia Dri-FIT', 89.99, 'Roupas', 100, true),
    ('Livro Clean Code', 'Livro sobre boas práticas de programação', 85.50, 'Livros', 30, true),
    ('Headphone Sony WH-1000XM4', 'Headphone com cancelamento de ruído', 1299.99, 'Eletrônicos', 15, true)
) AS temp(nome, descricao, preco, categoria, estoque, ativo)
WHERE NOT EXISTS (SELECT 1 FROM produtos);

-- Inserir clientes de exemplo
INSERT INTO cliente (nome, email, telefone, cpf, ativo)
SELECT * FROM (VALUES
    ('João da Silva', 'joao@email.com', '11999999999', '123.456.789-10', true),
    ('Maria Oliveira', 'maria@email.com', '21988888888', '123.456.789-11', true),
    ('Carlos Souza', 'carlos@email.com', '31977777777', '123.456.789-12', false)
) AS temp(nome, email, telefone, ativo)
WHERE NOT EXISTS (SELECT 1 FROM cliente);

-- Inserir pedidos de exemplo
INSERT INTO pedido (cliente_id, valor_total, status)
SELECT c.id, 0, 'PENDENTE' FROM cliente c WHERE NOT EXISTS (SELECT 1 FROM pedido);

-- Inserir itens de pedido de exemplo
INSERT INTO pedido_item (pedido_id, produto_id, quantidade)
SELECT p.id, pr.id, 2 FROM pedido p, produtos pr WHERE NOT EXISTS (SELECT 1 FROM pedido_item) LIMIT 1;
