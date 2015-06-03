# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  id                        bigint auto_increment not null,
  period                    integer,
  year                      integer,
  constraint pk_activity primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity;

SET REFERENTIAL_INTEGRITY TRUE;

