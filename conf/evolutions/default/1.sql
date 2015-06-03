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

create table semester (
  id                        bigint auto_increment not null,
  period                    integer,
  year                      integer,
  constraint pk_semester primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity;

drop table if exists semester;

SET REFERENTIAL_INTEGRITY TRUE;

