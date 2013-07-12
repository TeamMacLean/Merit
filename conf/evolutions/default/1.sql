# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alignment_object (
  name                      varchar(255),
  url                       varchar(255),
  description               varchar(255))
;

create table badge_assertion (
  uid                       varchar(255) not null,
  image                     varchar(255),
  evidence                  varchar(255),
  issued_on                 timestamp,
  badge                     varchar(255),
  expires                   timestamp,
  constraint pk_badge_assertion primary key (uid))
;

create table badge_class (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  image                     varchar(255),
  criteria                  varchar(255),
  issuer                    varchar(255),
  tags_one_line             varchar(255),
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
  uid                       bigint,
  reason                    varchar(255))
;

create table verification_object (
  type                      integer,
  url                       varchar(255),
  constraint ck_verification_object_type check (type in (0,1)))
;

create sequence badge_assertion_seq;

create sequence badge_class_seq;

create sequence image_seq;

create sequence issuer_organization_seq;




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

drop table if exists verification_object;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists badge_assertion_seq;

drop sequence if exists badge_class_seq;

drop sequence if exists image_seq;

drop sequence if exists issuer_organization_seq;

