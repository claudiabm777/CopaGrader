# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  deadline                  timestamp,
  creation_date             timestamp,
  semester_id               bigint,
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
  activity_id               bigint,
  constraint pk_admin primary key (id))
;

create table bullet (
  id                        bigint auto_increment not null,
  description               clob,
  task_id                   bigint,
  constraint pk_bullet primary key (id))
;

create table claim (
  id                        bigint auto_increment not null,
  creation_date             timestamp,
  processor_id              bigint,
  new_option_id             bigint,
  constraint uq_claim_processor_id unique (processor_id),
  constraint uq_claim_new_option_id unique (new_option_id),
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
  claim_id                  bigint,
  major_criterion_id        bigint,
  constraint uq_criterion_claim_id unique (claim_id),
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
  bullet_id                 bigint,
  constraint pk_major_criterion primary key (id))
;

create table option (
  id                        bigint auto_increment not null,
  description               clob,
  score                     double,
  is_selected               boolean,
  is_penalty                boolean,
  criterion_id              bigint,
  constraint pk_option primary key (id))
;

create table option_request (
  id                        bigint auto_increment not null,
  description               clob,
  is_penalty                boolean,
  creation_date             timestamp,
  postulator_id             bigint,
  criterion_id              bigint,
  constraint uq_option_request_postulator_id unique (postulator_id),
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
  semester_id               bigint,
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
  activity_id               bigint,
  constraint pk_task primary key (id))
;

create table team (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  activity_id               bigint,
  grader_id                 bigint,
  constraint pk_team primary key (id))
;


create table activity_admin (
  activity_id                    bigint not null,
  admin_id                       bigint not null,
  constraint pk_activity_admin primary key (activity_id, admin_id))
;

create table admin_course (
  admin_id                       bigint not null,
  course_id                      bigint not null,
  constraint pk_admin_course primary key (admin_id, course_id))
;

create table course_semester (
  course_id                      bigint not null,
  semester_id                    bigint not null,
  constraint pk_course_semester primary key (course_id, semester_id))
;

create table semester_grader (
  semester_id                    bigint not null,
  grader_id                      bigint not null,
  constraint pk_semester_grader primary key (semester_id, grader_id))
;

create table team_student (
  team_id                        bigint not null,
  student_id                     bigint not null,
  constraint pk_team_student primary key (team_id, student_id))
;
alter table activity add constraint fk_activity_semester_1 foreign key (semester_id) references semester (id) on delete restrict on update restrict;
create index ix_activity_semester_1 on activity (semester_id);
alter table admin add constraint fk_admin_activity_2 foreign key (activity_id) references activity (id) on delete restrict on update restrict;
create index ix_admin_activity_2 on admin (activity_id);
alter table bullet add constraint fk_bullet_task_3 foreign key (task_id) references task (id) on delete restrict on update restrict;
create index ix_bullet_task_3 on bullet (task_id);
alter table claim add constraint fk_claim_processor_4 foreign key (processor_id) references admin (id) on delete restrict on update restrict;
create index ix_claim_processor_4 on claim (processor_id);
alter table claim add constraint fk_claim_newOption_5 foreign key (new_option_id) references option (id) on delete restrict on update restrict;
create index ix_claim_newOption_5 on claim (new_option_id);
alter table criterion add constraint fk_criterion_claim_6 foreign key (claim_id) references claim (id) on delete restrict on update restrict;
create index ix_criterion_claim_6 on criterion (claim_id);
alter table criterion add constraint fk_criterion_majorCriterion_7 foreign key (major_criterion_id) references major_criterion (id) on delete restrict on update restrict;
create index ix_criterion_majorCriterion_7 on criterion (major_criterion_id);
alter table major_criterion add constraint fk_major_criterion_bullet_8 foreign key (bullet_id) references bullet (id) on delete restrict on update restrict;
create index ix_major_criterion_bullet_8 on major_criterion (bullet_id);
alter table option add constraint fk_option_criterion_9 foreign key (criterion_id) references criterion (id) on delete restrict on update restrict;
create index ix_option_criterion_9 on option (criterion_id);
alter table option_request add constraint fk_option_request_postulator_10 foreign key (postulator_id) references grader (id) on delete restrict on update restrict;
create index ix_option_request_postulator_10 on option_request (postulator_id);
alter table option_request add constraint fk_option_request_criterion_11 foreign key (criterion_id) references criterion (id) on delete restrict on update restrict;
create index ix_option_request_criterion_11 on option_request (criterion_id);
alter table student add constraint fk_student_semester_12 foreign key (semester_id) references semester (id) on delete restrict on update restrict;
create index ix_student_semester_12 on student (semester_id);
alter table task add constraint fk_task_activity_13 foreign key (activity_id) references activity (id) on delete restrict on update restrict;
create index ix_task_activity_13 on task (activity_id);
alter table team add constraint fk_team_activity_14 foreign key (activity_id) references activity (id) on delete restrict on update restrict;
create index ix_team_activity_14 on team (activity_id);
alter table team add constraint fk_team_grader_15 foreign key (grader_id) references grader (id) on delete restrict on update restrict;
create index ix_team_grader_15 on team (grader_id);



alter table activity_admin add constraint fk_activity_admin_activity_01 foreign key (activity_id) references activity (id) on delete restrict on update restrict;

alter table activity_admin add constraint fk_activity_admin_admin_02 foreign key (admin_id) references admin (id) on delete restrict on update restrict;

alter table admin_course add constraint fk_admin_course_admin_01 foreign key (admin_id) references admin (id) on delete restrict on update restrict;

alter table admin_course add constraint fk_admin_course_course_02 foreign key (course_id) references course (id) on delete restrict on update restrict;

alter table course_semester add constraint fk_course_semester_course_01 foreign key (course_id) references course (id) on delete restrict on update restrict;

alter table course_semester add constraint fk_course_semester_semester_02 foreign key (semester_id) references semester (id) on delete restrict on update restrict;

alter table semester_grader add constraint fk_semester_grader_semester_01 foreign key (semester_id) references semester (id) on delete restrict on update restrict;

alter table semester_grader add constraint fk_semester_grader_grader_02 foreign key (grader_id) references grader (id) on delete restrict on update restrict;

alter table team_student add constraint fk_team_student_team_01 foreign key (team_id) references team (id) on delete restrict on update restrict;

alter table team_student add constraint fk_team_student_student_02 foreign key (student_id) references student (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity;

drop table if exists activity_admin;

drop table if exists admin;

drop table if exists admin_course;

drop table if exists bullet;

drop table if exists claim;

drop table if exists course;

drop table if exists course_semester;

drop table if exists criterion;

drop table if exists grader;

drop table if exists major_criterion;

drop table if exists option;

drop table if exists option_request;

drop table if exists semester;

drop table if exists semester_grader;

drop table if exists student;

drop table if exists super_admin;

drop table if exists task;

drop table if exists team;

drop table if exists team_student;

SET REFERENTIAL_INTEGRITY TRUE;

