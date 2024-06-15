create table roteiros(

   id bigint not null auto_increment primary key,
   origem varchar(255) not null,
   destino varchar(255) not null,
   gasto decimal(10,2) not null,
   voo varchar(50),
   data_saida date,
   data_retorno date,
   usuario_id bigint not null,
   foreign key (usuario_id) references usuarios(id) on delete cascade,
   constraint chk_gasto_esperado check (gasto >= 0)
);