alter table videos add categoriaId bigint not null;
update videos set categoriaId = 1;