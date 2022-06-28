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
INSERT IGNORE INTO produto (id,nome,descricao,quantidade_estoque,data_encomenda,preco_venda,preco_compra,ativo,categoria_id) VALUES (1,'Biscoito de sal', 'Biscoito feito de sal. O melhor que existe', 25, '2022-05-26', 8.0, 1.75, 1, 1);
INSERT IGNORE INTO produto (id,nome,descricao,quantidade_estoque,data_encomenda,preco_venda,preco_compra,ativo,categoria_id) VALUES (2,'Queijo qualha', 'Queijo feito com leite retirado direto da vaca', 20, '2022-05-26', 15.0, 6, 1, 2);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,preco_total,data_cadastro,produto_id) VALUES (15,1.5,22.5,'2022-01-25',1);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,preco_total,data_cadastro,produto_id) VALUES (10,1.75,17.5,'2022-05-26',1);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,preco_total,data_cadastro,produto_id) VALUES (12,5.75,69,'2022-01-25',2);
INSERT IGNORE INTO produto_encomenda (quantidade,preco_compra,preco_total,data_cadastro,produto_id) VALUES (8,6,48,'2022-05-26',2);

INSERT IGNORE INTO permissao (id, nome, descricao) values (1, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
INSERT IGNORE INTO permissao (id, nome, descricao) values (2, 'EDITAR_PRODUTOS', 'Permite editar produtos');

INSERT IGNORE INTO grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

INSERT IGNORE INTO grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);