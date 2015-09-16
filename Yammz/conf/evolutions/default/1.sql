# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table conductor (
  id                        bigserial not null,
  nombre                    varchar(255),
  cedula                    integer,
  celular                   integer,
  correo                    varchar(255),
  desempenio                float,
  viajes_totales            integer,
  constraint pk_conductor primary key (id))
;

create table direccion (
  principal                 integer,
  numero                    integer,
  detalles                  varchar(255))
;

create table estacion_vcub (
  id                        bigserial not null,
  capacidad                 integer,
  vcubs                     integer,
  nombre                    varchar(255),
  envio_reporte             boolean,
  constraint pk_estacion_vcub primary key (id))
;

create table movibus (
  id                        bigserial not null,
  posicion                  varchar(255),
  estado                    integer,
  kilometraje               integer,
  constraint pk_movibus primary key (id))
;

create table pedido_movibus (
  id                        bigserial not null,
  fecha_pedido              timestamp,
  fecha_ejecucion           timestamp,
  tiempo_estimado           integer,
  tiempo_real               integer,
  usuario_id                bigint,
  movibus_id                bigint,
  conductor_id              bigint,
  direccion_usuario         varchar(255),
  direccion_destino         varchar(255),
  constraint uq_pedido_movibus_usuario_id unique (usuario_id),
  constraint uq_pedido_movibus_movibus_id unique (movibus_id),
  constraint uq_pedido_movibus_conductor_id unique (conductor_id),
  constraint pk_pedido_movibus primary key (id))
;

create table pedido_movibus_pendiente (
  id                        bigserial not null,
  usuario_id                bigint,
  direccion_usuario         varchar(255),
  direccion_destino         varchar(255),
  constraint uq_pedido_movibus_pendiente_usua unique (usuario_id),
  constraint pk_pedido_movibus_pendiente primary key (id))
;

create table reporte (
  id                        bigserial not null,
  tipo_reporte              varchar(255),
  descripcion               varchar(255),
  tipo_accidente            integer,
  magnitud                  varchar(255),
  constraint pk_reporte primary key (id))
;

create table tranvia (
  id                        bigserial not null,
  posicion                  varchar(255),
  estado                    integer,
  kilometraje               integer,
  linea                     integer,
  constraint pk_tranvia primary key (id))
;

create table usuario (
  id                        bigserial not null,
  nombre                    varchar(255),
  cedula                    integer,
  celular                   integer,
  correo                    varchar(255),
  vcubs_en_uso              integer,
  tarjeta_bancaria          bigint,
  constraint pk_usuario primary key (id))
;

alter table pedido_movibus add constraint fk_pedido_movibus_usuario_1 foreign key (usuario_id) references usuario (id);
create index ix_pedido_movibus_usuario_1 on pedido_movibus (usuario_id);
alter table pedido_movibus add constraint fk_pedido_movibus_movibus_2 foreign key (movibus_id) references movibus (id);
create index ix_pedido_movibus_movibus_2 on pedido_movibus (movibus_id);
alter table pedido_movibus add constraint fk_pedido_movibus_conductor_3 foreign key (conductor_id) references conductor (id);
create index ix_pedido_movibus_conductor_3 on pedido_movibus (conductor_id);
alter table pedido_movibus_pendiente add constraint fk_pedido_movibus_pendiente_us_4 foreign key (usuario_id) references usuario (id);
create index ix_pedido_movibus_pendiente_us_4 on pedido_movibus_pendiente (usuario_id);



# --- !Downs

drop table if exists conductor cascade;

drop table if exists direccion cascade;

drop table if exists estacion_vcub cascade;

drop table if exists movibus cascade;

drop table if exists pedido_movibus cascade;

drop table if exists pedido_movibus_pendiente cascade;

drop table if exists reporte cascade;

drop table if exists tranvia cascade;

drop table if exists usuario cascade;

