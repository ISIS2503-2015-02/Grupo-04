# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table conductor (
  id                        bigint auto_increment not null,
  nombre                    varchar(255),
  cedula                    integer,
  celular                   integer,
  correo                    varchar(255),
  constraint pk_conductor primary key (id))
;

create table direccion (
  calle                     integer,
  carrera                   integer,
  numero                    integer,
  detalles                  varchar(255))
;

create table estacion_vcub (
  id                        bigint auto_increment not null,
  capacidad                 integer,
  vcubs                     integer,
  nombre                    varchar(255),
  constraint pk_estacion_vcub primary key (id))
;

create table movibus (
  id                        bigint auto_increment not null,
  estado                    integer,
  kilometraje               integer,
  constraint pk_movibus primary key (id))
;

create table pedido_movibus (
  fecha_pedido              timestamp,
  fecha_ejecucion           timestamp,
  tiempo_estimado           integer,
  tiempo_real               integer)
;

create table reporte (
  id                        integer auto_increment not null,
  tipo_reporte              varchar(255),
  descripcion               varchar(255),
  constraint pk_reporte primary key (id))
;

create table tranvia (
  id                        bigint auto_increment not null,
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

create table vehiculo (
  estado                    integer,
  kilometraje               integer)
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists conductor;

drop table if exists direccion;

drop table if exists estacion_vcub;

drop table if exists movibus;

drop table if exists pedido_movibus;

drop table if exists reporte;

drop table if exists tranvia;

drop table if exists usuario;

drop table if exists vehiculo;

SET REFERENTIAL_INTEGRITY TRUE;

