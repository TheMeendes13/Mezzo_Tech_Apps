create table usuarios(

   id bigint not null auto_increment,
   nome varchar(100),
   email varchar(100),
   genero varchar(20),
   cidade varchar(100),
   estado varchar(100),
   pais varchar(100),

   primary key(id)
);