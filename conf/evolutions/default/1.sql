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
  name                      varchar(255),
  description               varchar(255),
  image                     varchar(255),
  criteria                  varchar(255),
  issuer                    varchar(255))
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

create table issuer_organization (
  name                      varchar(255),
  url                       varchar(255),
  description               varchar(255),
  image                     varchar(255),
  email                     varchar(255),
  revocation_list           varchar(255))
;

create table verification_object (
  type                      integer,
  url                       varchar(255),
  constraint ck_verification_object_type check (type in (0,1)))
;

create sequence badge_assertion_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists alignment_object;

drop table if exists badge_assertion;

drop table if exists badge_class;

drop table if exists identity_hash;

drop table if exists identity_object;

drop table if exists issuer_organization;

drop table if exists verification_object;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists badge_assertion_seq;

