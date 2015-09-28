-- Project Name : SpringApp
-- Date/Time    : 2015/09/28 9:46:00
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 制約検証
create table verify_constraints (
  id BIGINT not null AUTO_INCREMENT
  , parent_id BIGINT
  , name VARCHAR(32)
  , constraint verify_constraints_PKC primary key (id)
) ;

alter table verify_constraints add constraint verify_constraints_IX1
  unique (name) ;

-- SQL実行検証
create table sql_execution (
  id BIGINT not null AUTO_INCREMENT
  , login VARCHAR(32)
  , name VARCHAR(32)
  , constraint sql_execution_PKC primary key (id)
) ;

-- ETL検証
create table etl_extr_ldr (
  id BIGINT not null AUTO_INCREMENT
  , name VARCHAR(32) not null
  , address VARCHAR(64) not null
  , constraint etl_extr_ldr_PKC primary key (id)
) ;

-- フラグ操作検証
create table verify_flag (
  id BIGINT not null AUTO_INCREMENT
  , flag_code INTEGER
  , deleted_flg INTEGER
  , constraint verify_flag_PKC primary key (id)
) ;

-- 暗号操作検証
create table verify_secure (
  id BIGINT not null AUTO_INCREMENT
  , str VARCHAR(136)
  , int32 VARCHAR(72)
  , int64 VARCHAR(72)
  , bigint VARCHAR(136)
  , bigdec VARCHAR(136)
  , constraint verify_secure_PKC primary key (id)
) ;

-- 日時操作検証
create table verify_datetime (
  id BIGINT not null AUTO_INCREMENT
  , dt DATE
  , tm TIME
  , dtm TIMESTAMP
  , constraint verify_datetime_PKC primary key (id)
) ;

alter table verify_constraints
  add constraint verify_constraints_FK1 foreign key (parent_id) references verify_constraints(id);

comment on table verify_constraints is '制約検証';
comment on column verify_constraints.id is 'ID';
comment on column verify_constraints.parent_id is '親ID';
comment on column verify_constraints.name is '名称';

comment on table sql_execution is 'SQL実行検証';
comment on column sql_execution.id is 'ID';
comment on column sql_execution.login is 'ログイン';
comment on column sql_execution.name is '名前';

comment on table etl_extr_ldr is 'ETL検証';
comment on column etl_extr_ldr.id is 'ID';
comment on column etl_extr_ldr.name is '名前';
comment on column etl_extr_ldr.address is '住所';

comment on table verify_flag is 'フラグ操作検証';
comment on column verify_flag.id is 'ID';
comment on column verify_flag.flag_code is 'フラグ';
comment on column verify_flag.deleted_flg is '削除フラグ';

comment on table verify_secure is '暗号操作検証';
comment on column verify_secure.id is 'ID';
comment on column verify_secure.str is '文字列';
comment on column verify_secure.int32 is '整数【32bit】';
comment on column verify_secure.int64 is '整数【64bit】';
comment on column verify_secure.bigint is '整数【BigInteger】';
comment on column verify_secure.bigdec is '数値【BigDecimal】';

comment on table verify_datetime is '日時操作検証';
comment on column verify_datetime.id is 'ID';
comment on column verify_datetime.dt is '日付';
comment on column verify_datetime.tm is '時刻';
comment on column verify_datetime.dtm is '日時';
