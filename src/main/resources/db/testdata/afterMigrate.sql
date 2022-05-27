set foreign_key_checks = 0;

DELETE FROM grupo;
DELETE FROM grupo_permissao; 
DELETE FROM pedido; 
DELETE FROM pedido_item; 
DELETE FROM permissao; 
DELETE FROM produto; 
DELETE FROM produto_categoria; 
DELETE FROM produto_encomenda; 
DELETE FROM usuario; 
DELETE FROM usuario_grupo;

set foreign_key_checks = 1;


ALTER TABLE grupo auto_increment = 1;
ALTER TABLE pedido auto_increment = 1; 
ALTER TABLE pedido_item auto_increment = 1; 
ALTER TABLE permissao auto_increment = 1; 
ALTER TABLE produto auto_increment = 1; 
ALTER TABLE produto_categoria auto_increment = 1; 
ALTER TABLE produto_encomenda auto_increment = 1; 
ALTER TABLE usuario auto_increment = 1; 

INSERT IGNORE INTO produto_categoria (id,nome) VALUES (1,'Biscoito');
INSERT IGNORE INTO produto_categoria (id,nome) VALUES (2,'Queijo');
INSERT IGNORE INTO produto (id,nome,descricao,preco_venda,ativo,categoria_id) VALUES (1,'Biscoito de sal', 'Biscoito feito de sal. O melhor que existe', 8.0, 1, 1);
INSERT IGNORE INTO produto (id,nome,descricao,preco_venda,ativo,categoria_id) VALUES (2,'Queijo qualha', 'Queijo feito com leite retirado direto da vaca', 15.0, 1, 2);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,data_cadastro,produto_id) VALUES (15,1.5,'2022-01-25T23:32:01',1);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,data_cadastro,produto_id) VALUES (10,1.75,'2022-05-26T23:32:01',1);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,data_cadastro,produto_id) VALUES (12,5.75,'2022-01-25T23:32:01',2);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,data_cadastro,produto_id) VALUES (8,6,'2022-05-26T23:32:01',2);
