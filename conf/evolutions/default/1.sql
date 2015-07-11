# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  id                        bigserial not null,
  semester_id               bigint not null,
  name                      varchar(255),
  deadline                  timestamp,
  creation_date             timestamp,
  admins_in_charge          varchar(255),
  constraint pk_activity primary key (id))
;

create table admin (
  email                     varchar(255) not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             varchar(255),
  phone                     varchar(255),
  password                  varchar(255),
  enable                    boolean,
  constraint pk_admin primary key (email))
;

create table bullet (
  id                        bigserial not null,
  task_id                   bigint not null,
  description               text,
  constraint pk_bullet primary key (id))
;

create table claim (
  id                        bigserial not null,
  creation_date             timestamp,
  processor_email           varchar(255),
  new_option_id             bigint,
  constraint uq_claim_processor_email unique (processor_email),
  constraint uq_claim_new_option_id unique (new_option_id),
  constraint pk_claim primary key (id))
;

create table course (
  code                      varchar(255) not null,
  name                      varchar(255),
  credits                   integer,
  department                varchar(255),
  crn                       integer,
  constraint pk_course primary key (code))
;

create table criterion (
  id                        bigserial not null,
  major_criterion_id        bigint not null,
  description               text,
  claim_id                  bigint,
  constraint uq_criterion_claim_id unique (claim_id),
  constraint pk_criterion primary key (id))
;

create table grader (
  email                     varchar(255) not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             varchar(255),
  phone                     varchar(255),
  password                  varchar(255),
  enable                    boolean,
  cargo                     integer,
  constraint pk_grader primary key (email))
;

create table major_criterion (
  id                        bigserial not null,
  bullet_id                 bigint not null,
  description               text,
  constraint pk_major_criterion primary key (id))
;

create table option (
  id                        bigserial not null,
  criterion_id              bigint not null,
  description               text,
  score                     float,
  is_selected               boolean,
  is_penalty                boolean,
  constraint pk_option primary key (id))
;

create table option_request (
  id                        bigserial not null,
  criterion_id              bigint not null,
  description               text,
  is_penalty                boolean,
  creation_date             timestamp,
  postulator_email          varchar(255),
  constraint uq_option_request_postulator_ema unique (postulator_email),
  constraint pk_option_request primary key (id))
;

create table semester (
  id                        bigserial not null,
  course_code               varchar(255) not null,
  period                    varchar(255),
  constraint pk_semester primary key (id))
;

create table student (
  id                        bigserial not null,
  semester_id               bigint not null,
  names                     varchar(255),
  last_names                varchar(255),
  code                      varchar(255),
  career                    varchar(255),
  email                     varchar(255),
  magis_section             integer,
  compl_section             integer,
  constraint pk_student primary key (id))
;

create table super_admin (
  email                     varchar(255) not null,
  names                     varchar(255),
  last_names                varchar(255),
  identity_card             varchar(255),
  phone                     varchar(255),
  password                  varchar(255),
  constraint pk_super_admin primary key (email))
;

create table task (
  id                        bigserial not null,
  activity_id               bigint not null,
  name                      varchar(255),
  constraint pk_task primary key (id))
;

create table team (
  id                        bigserial not null,
  activity_id               bigint not null,
  name                      varchar(255),
  constraint pk_team primary key (id))
;


create table admin_course (
  admin_email                    varchar(255) not null,
  course_code                    varchar(255) not null,
  constraint pk_admin_course primary key (admin_email, course_code))
;

create table grader_team (
  grader_email                   varchar(255) not null,
  team_id                        bigint not null,
  constraint pk_grader_team primary key (grader_email, team_id))
;

create table semester_grader (
  semester_id                    bigint not null,
  grader_email                   varchar(255) not null,
  constraint pk_semester_grader primary key (semester_id, grader_email))
;

create table team_student (
  team_id                        bigint not null,
  student_id                     bigint not null,
  constraint pk_team_student primary key (team_id, student_id))
;
alter table activity add constraint fk_activity_semester_1 foreign key (semester_id) references semester (id);
create index ix_activity_semester_1 on activity (semester_id);
alter table bullet add constraint fk_bullet_task_2 foreign key (task_id) references task (id);
create index ix_bullet_task_2 on bullet (task_id);
alter table claim add constraint fk_claim_processor_3 foreign key (processor_email) references admin (email);
create index ix_claim_processor_3 on claim (processor_email);
alter table claim add constraint fk_claim_newOption_4 foreign key (new_option_id) references option (id);
create index ix_claim_newOption_4 on claim (new_option_id);
alter table criterion add constraint fk_criterion_major_criterion_5 foreign key (major_criterion_id) references major_criterion (id);
create index ix_criterion_major_criterion_5 on criterion (major_criterion_id);
alter table criterion add constraint fk_criterion_claim_6 foreign key (claim_id) references claim (id);
create index ix_criterion_claim_6 on criterion (claim_id);
alter table major_criterion add constraint fk_major_criterion_bullet_7 foreign key (bullet_id) references bullet (id);
create index ix_major_criterion_bullet_7 on major_criterion (bullet_id);
alter table option add constraint fk_option_criterion_8 foreign key (criterion_id) references criterion (id);
create index ix_option_criterion_8 on option (criterion_id);
alter table option_request add constraint fk_option_request_criterion_9 foreign key (criterion_id) references criterion (id);
create index ix_option_request_criterion_9 on option_request (criterion_id);
alter table option_request add constraint fk_option_request_postulator_10 foreign key (postulator_email) references grader (email);
create index ix_option_request_postulator_10 on option_request (postulator_email);
alter table semester add constraint fk_semester_course_11 foreign key (course_code) references course (code);
create index ix_semester_course_11 on semester (course_code);
alter table student add constraint fk_student_semester_12 foreign key (semester_id) references semester (id);
create index ix_student_semester_12 on student (semester_id);
alter table task add constraint fk_task_activity_13 foreign key (activity_id) references activity (id);
create index ix_task_activity_13 on task (activity_id);
alter table team add constraint fk_team_activity_14 foreign key (activity_id) references activity (id);
create index ix_team_activity_14 on team (activity_id);



alter table admin_course add constraint fk_admin_course_admin_01 foreign key (admin_email) references admin (email);

alter table admin_course add constraint fk_admin_course_course_02 foreign key (course_code) references course (code);

alter table grader_team add constraint fk_grader_team_grader_01 foreign key (grader_email) references grader (email);

alter table grader_team add constraint fk_grader_team_team_02 foreign key (team_id) references team (id);

alter table semester_grader add constraint fk_semester_grader_semester_01 foreign key (semester_id) references semester (id);

alter table semester_grader add constraint fk_semester_grader_grader_02 foreign key (grader_email) references grader (email);

alter table team_student add constraint fk_team_student_team_01 foreign key (team_id) references team (id);

alter table team_student add constraint fk_team_student_student_02 foreign key (student_id) references student (id);

# --- !Downs

drop table if exists activity cascade;

drop table if exists admin cascade;

drop table if exists admin_course cascade;

drop table if exists bullet cascade;

drop table if exists claim cascade;

drop table if exists course cascade;

drop table if exists criterion cascade;

drop table if exists grader cascade;

drop table if exists grader_team cascade;

drop table if exists major_criterion cascade;

drop table if exists option cascade;

drop table if exists option_request cascade;

drop table if exists semester cascade;

drop table if exists semester_grader cascade;

drop table if exists student cascade;

drop table if exists super_admin cascade;

drop table if exists task cascade;

drop table if exists team cascade;

drop table if exists team_student cascade;

