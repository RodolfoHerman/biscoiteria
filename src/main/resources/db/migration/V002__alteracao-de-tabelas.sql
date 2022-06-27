ALTER TABLE produto_encomenda ADD preco_total DECIMAL(10,2) NOT NULL AFTER preco_compra;
ALTER TABLE produto_encomenda MODIFY data_cadastro DATE;

ALTER TABLE produto ADD data_encomenda DATE;
ALTER TABLE produto ADD quantidade_estoque INTEGER DEFAULT 0;
ALTER TABLE produto ADD preco_compra DECIMAL(10,2) AFTER preco_venda;

ALTER TABLE pedido MODIFY data_criacao DATE; 
ALTER TABLE pedido ADD preco_total DECIMAL(10,2) NOT NULL;
ALTER TABLE pedido ADD lucro_total DECIMAL(10,2) NOT NULL;

ALTER TABLE pedido_item ADD preco_total DECIMAL(10,2) NOT NULL AFTER preco;
ALTER TABLE pedido_item ADD preco_compra_produto DECIMAL(10,2) NOT NULL AFTER preco_total;
ALTER TABLE pedido_item ADD lucro DECIMAL(10,2) NOT NULL AFTER preco_compra_produto;