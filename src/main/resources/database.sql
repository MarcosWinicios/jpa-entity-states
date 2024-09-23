create table tb_client (
   id bigint not null auto_increment,
   name varchar(60) not null,
   primary key (id)
) engine=InnoDB;

insert into tb_client (id, name) values (1, 'Autopeças Estrada');