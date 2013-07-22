# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alignment_object (
  id                        bigint not null,
  name                      varchar(255),
  url                       varchar(255),
  description               varchar(255),
  constraint pk_alignment_object primary key (id))
;

create table badge_assertion (
  uid                       varchar(255) not null,
  image                     varchar(255),
  evidence                  varchar(255),
  issued_on                 bigint,
  badge                     varchar(255),
  constraint pk_badge_assertion primary key (uid))
;

create table badge_class (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  image                     varchar(255),
  criteria                  varchar(255),
  issuer                    varchar(255),
  alignment                 bigint,
  tags_one_line             varchar(255),
  alignment_string          varchar(255),
  issuer_string             varchar(255),
  constraint pk_badge_class primary key (id))
;

create table identity_hash (
  salt                      varchar(255),
  digest                    varchar(255))
;

create table identity_object (
  type                      integer,
  hashed                    boolean,
  salt                      varchar(255),
  identity                  varchar(255),
  constraint ck_identity_object_type check (type in (0)))
;

create table image (
  id                        bigint not null,
  url                       varchar(255),
  image_type                integer,
  name                      varchar(255),
  constraint ck_image_image_type check (image_type in (0,1)),
  constraint pk_image primary key (id))
;

create table issuer_organization (
  id                        bigint not null,
  name                      varchar(255),
  url                       varchar(255),
  description               varchar(255),
  image                     varchar(255),
  email                     varchar(255),
  revocation_list           varchar(255),
  constraint pk_issuer_organization primary key (id))
;

create table revocation (
  uid                       bigint not null,
  reason                    varchar(255),
  constraint pk_revocation primary key (uid))
;

create table tag (
  id                        bigint not null,
  value                     varchar(255),
  assigned_to_id            bigint,
  constraint pk_tag primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  api_key                   varchar(255),
  constraint pk_user primary key (id))
;

create table verification_object (
  type                      integer,
  url                       varchar(255),
  constraint ck_verification_object_type check (type in (0,1)))
;

create sequence alignment_object_seq;

create sequence badge_assertion_seq;

create sequence badge_class_seq;

create sequence image_seq;

create sequence issuer_organization_seq;

create sequence revocation_seq;

create sequence tag_seq;

create sequence user_seq;

alter table tag add constraint fk_tag_assignedTo_1 foreign key (assigned_to_id) references badge_class (id) on delete restrict on update restrict;
create index ix_tag_assignedTo_1 on tag (assigned_to_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists alignment_object;

drop table if exists badge_assertion;

drop table if exists badge_class;

drop table if exists identity_hash;

drop table if exists identity_object;

drop table if exists image;

drop table if exists issuer_organization;

drop table if exists revocation;

drop table if exists tag;

drop table if exists user;

drop table if exists verification_object;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists alignment_object_seq;

drop sequence if exists badge_assertion_seq;

drop sequence if exists badge_class_seq;

drop sequence if exists image_seq;

drop sequence if exists issuer_organization_seq;

drop sequence if exists revocation_seq;

drop sequence if exists tag_seq;

drop sequence if exists user_seq;

