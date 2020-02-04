create database mensagemjsf;

use mensagemjsf;
 
create table mensagem(
codigo int not null primary key auto_increment,
nome char(20),
email char(20),
usuario char(10),
senha char (10)
);


