# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  deadline                  timestamp,
  creation_date             timestamp,
  constraint pk_activity primary key (id))
;

create table admin (
  id                        bigint auto_increment not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             bigint,
  phone                     bigint,
  email                     varchar(255),
  contrasenia               varchar(255),
  habilitado                boolean,
  constraint pk_admin primary key (id))
;

create table bullet (
  id                        bigint auto_increment not null,
  description               clob,
  constraint pk_bullet primary key (id))
;

create table claim (
  id                        bigint auto_increment not null,
  creation_date             timestamp,
  constraint pk_claim primary key (id))
;

create table course (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  credits                   integer,
  department                varchar(255),
  code                      varchar(255),
  crn                       integer,
  constraint pk_course primary key (id))
;

create table criterion (
  id                        bigint auto_increment not null,
  description               clob,
  constraint pk_criterion primary key (id))
;

create table grader (
  id                        bigint auto_increment not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             bigint,
  phone                     bigint,
  email                     varchar(255),
  contrasenia               varchar(255),
  habilitado                boolean,
  cargo                     integer,
  constraint pk_grader primary key (id))
;

create table major_criterion (
  id                        bigint auto_increment not null,
  description               clob,
  constraint pk_major_criterion primary key (id))
;

create table option (
  id                        bigint auto_increment not null,
  description               clob,
  score                     double,
  is_selected               boolean,
  is_penalty                boolean,
  constraint pk_option primary key (id))
;

create table option_request (
  id                        bigint auto_increment not null,
  description               clob,
  is_penalty                boolean,
  creation_date             timestamp,
  constraint pk_option_request primary key (id))
;

create table semester (
  id                        bigint auto_increment not null,
  period                    integer,
  year                      integer,
  constraint pk_semester primary key (id))
;

create table student (
  id                        bigint auto_increment not null,
  names                     varchar(255),
  last_names                varchar(255),
  code                      integer,
  career                    varchar(255),
  email                     varchar(255),
  magis_section             integer,
  compl_section             integer,
  constraint pk_student primary key (id))
;

create table super_admin (
  id                        bigint auto_increment not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             bigint,
  phone                     bigint,
  email                     varchar(255),
  contrasenia               varchar(255),
  constraint pk_super_admin primary key (id))
;

create table task (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_task primary key (id))
;

create table team (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_team primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity;

drop table if exists admin;

drop table if exists bullet;

drop table if exists claim;

drop table if exists course;

drop table if exists criterion;

drop table if exists grader;

drop table if exists major_criterion;

drop table if exists option;

drop table if exists option_request;

drop table if exists semester;

drop table if exists student;

drop table if exists super_admin;

drop table if exists task;

drop table if exists team;

SET REFERENTIAL_INTEGRITY TRUE;

