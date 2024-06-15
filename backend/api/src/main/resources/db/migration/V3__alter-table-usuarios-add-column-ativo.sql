alter table usuarios add ativo tinyint;
update usuarios set ativo = 1;
alter table usuarios modify ativo tinyint not null;