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