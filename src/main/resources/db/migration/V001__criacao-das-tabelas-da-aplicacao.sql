create table grupo (
    id bigint not null auto_increment,
    nome varchar(50) not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null
) engine=InnoDB default charset=UTF8MB4;

create table pedido (
    id bigint not null auto_increment,
    data_cancelamento datetime(6),
    data_criacao datetime(6) not null,
    data_pagamento datetime(6),
    forma_pagamento varchar(50) not null,
    status varchar(20) not null,
    usuario_cliente_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table pedido_item (
    id bigint not null auto_increment,
    preco decimal(10,2) not null,
    quantidade integer not null,
    pedido_id bigint not null,
    produto_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table permissao (
    id bigint not null auto_increment,
    descricao varchar(50) not null,
    nome varchar(50) not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table produto (
    id bigint not null auto_increment,
    ativo bit not null,
    descricao varchar(150) not null,
    nome varchar(50) not null,
    preco_venda decimal(10,2) not null,
    categoria_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table produto_categoria (
    id bigint not null auto_increment,
    nome varchar(50) not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table produto_encomenda (
    id bigint not null auto_increment,
    data_cadastro datetime,
    preco_compra decimal(10,2) not null,
    quantidade integer not null,
    produto_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table usuario (
    id bigint not null auto_increment,
    data_cadastro datetime,
    email varchar(100),
    endereco_bairro varchar(100) not null,
    endereco_complemento varchar(30),
    endereco_numero varchar(15) not null,
    endereco_rua varchar(100) not null,
    nome varchar(60) not null,
    telefone varchar(15) not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null
) engine=InnoDB default charset=UTF8MB4;

alter table grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id);

alter table pedido add constraint fk_pedido_cliente foreign key (usuario_cliente_id) references usuario (id);

alter table pedido_item add constraint fk_pedido_item_pedido foreign key (pedido_id) references pedido (id);

alter table pedido_item add constraint fk_pedido_item_produto foreign key (produto_id) references produto (id);

alter table produto add constraint fk_produto_categoria foreign key (categoria_id) references produto_categoria (id);

alter table produto_encomenda add constraint fk_produto_encomenda_produto foreign key (produto_id) references produto (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);
