alter table roteiros add ativo tinyint;
update roteiros set ativo = 1;
alter table roteiros modify ativo tinyint not null;
