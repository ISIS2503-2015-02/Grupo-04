# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table conductor (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  cedula                    integer,
  celular                   integer,
  correo                    varchar(255),
  desempenio                double,
  viajes_totales            integer,
  estado                    integer,
  constraint pk_conductor primary key (id))
;

create table estacion_vcub (
  id                        bigint auto_increment not null,
  capacidad                 integer,
  vcubs                     integer,
  nombre                    varchar(255),
  reporte                   boolean,
  constraint pk_estacion_vcub primary key (id))
;

create table movibus (
  id                        bigint auto_increment not null,
  latitud                   double,
  longitud                  double,
  estado                    integer,
  kilometraje               integer,
  constraint pk_movibus primary key (id))
;

create table pedido_movibus (
  id                        bigint auto_increment not null,
  fecha_pedido              timestamp,
  fecha_ejecucion           timestamp,
  tiempo_estimado           integer,
  tiempo_real               integer,
  usuario_id                bigint,
  movibus_id                bigint,
  conductor_id              bigint,
  latitud_usuario           double,
  longitud_usuario          double,
  latitud_destino           double,
  longitud_destino          double,
  constraint pk_pedido_movibus primary key (id))
;

create table pedido_movibus_pendiente (
  id                        bigint auto_increment not null,
  usuario_id                bigint,
  fecha_pedido              timestamp,
  fecha_ejecucion           timestamp,
  latitud_usuario           double,
  longitud_usuario          double,
  latitud_destino           double,
  longitud_destino          double,
  tiempo_estimado           integer,
  constraint pk_pedido_movibus_pendiente primary key (id))
;

create table reporte (
  id                        bigint auto_increment not null,
  tipo_reporte              varchar(255),
  descripcion               varchar(255),
  tipo_accidente            integer,
  magnitud                  varchar(255),
  constraint pk_reporte primary key (id))
;

create table tranvia (
  id                        bigint auto_increment not null,
  latitud                   double,
  longitud                  double,
  estado                    integer,
  kilometraje               integer,
  linea                     integer,
  constraint pk_tranvia primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  cedula                    integer,
  celular                   integer,
  correo                    varchar(255),
  vcubs_en_uso              integer,
  tarjeta_bancaria          bigint,
  constraint pk_usuario primary key (id))
;

alter table pedido_movibus add constraint fk_pedido_movibus_usuario_1 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_pedido_movibus_usuario_1 on pedido_movibus (usuario_id);
alter table pedido_movibus add constraint fk_pedido_movibus_movibus_2 foreign key (movibus_id) references movibus (id) on delete restrict on update restrict;
create index ix_pedido_movibus_movibus_2 on pedido_movibus (movibus_id);
alter table pedido_movibus add constraint fk_pedido_movibus_conductor_3 foreign key (conductor_id) references conductor (id) on delete restrict on update restrict;
create index ix_pedido_movibus_conductor_3 on pedido_movibus (conductor_id);
alter table pedido_movibus_pendiente add constraint fk_pedido_movibus_pendiente_us_4 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_pedido_movibus_pendiente_us_4 on pedido_movibus_pendiente (usuario_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists conductor;

drop table if exists estacion_vcub;

drop table if exists movibus;

drop table if exists pedido_movibus;

drop table if exists pedido_movibus_pendiente;

drop table if exists reporte;

drop table if exists tranvia;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

