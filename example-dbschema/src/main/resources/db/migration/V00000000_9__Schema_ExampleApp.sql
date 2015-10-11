-- Project Name : ExampleApp
-- Date/Time    : 2015/10/12 7:29:41
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- テーブル2
create table ex_tbl_2 (
  id BIGINT not null AUTO_INCREMENT
  , parent_id BIGINT not null
  , text10 VARCHAR(10)
  , text100 VARCHAR(100)
  , int64 BIGINT
  , decimal1 DECIMAL(11,1)
  , decimal3 DECIMAL(13,3)
  , dt DATE
  , tm TIME
  , dtm TIMESTAMP
  , code2 VARCHAR(2)
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint ex_tbl_2_PKC primary key (id)
) ;

create index ex_tbl_2_IX1
  on ex_tbl_2(parent_id);

-- テーブル1
create table ex_tbl_1 (
  id BIGINT not null AUTO_INCREMENT
  , text10 VARCHAR(10)
  , text100 VARCHAR(100)
  , int64 BIGINT
  , decimal1 DECIMAL(11,1)
  , decimal3 DECIMAL(13,3)
  , dt DATE
  , tm TIME
  , dtm TIMESTAMP
  , code2 VARCHAR(2)
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint ex_tbl_1_PKC primary key (id)
) ;

comment on table ex_tbl_2 is 'テーブル2';
comment on column ex_tbl_2.id is 'ID';
comment on column ex_tbl_2.parent_id is '親ID';
comment on column ex_tbl_2.text10 is '文字列【10】';
comment on column ex_tbl_2.text100 is '文字列【100】';
comment on column ex_tbl_2.int64 is '整数【64bit】';
comment on column ex_tbl_2.decimal1 is '小数【1桁】';
comment on column ex_tbl_2.decimal3 is '小数【3桁】';
comment on column ex_tbl_2.dt is '日付';
comment on column ex_tbl_2.tm is '時刻';
comment on column ex_tbl_2.dtm is '日時';
comment on column ex_tbl_2.code2 is '区分値【2】';
comment on column ex_tbl_2.updated_at is '更新日時';
comment on column ex_tbl_2.created_at is '作成日時';
comment on column ex_tbl_2.lock_version is 'ロックバージョン';

comment on table ex_tbl_1 is 'テーブル1';
comment on column ex_tbl_1.id is 'ID';
comment on column ex_tbl_1.text10 is '文字列【10】';
comment on column ex_tbl_1.text100 is '文字列【100】';
comment on column ex_tbl_1.int64 is '整数【64bit】';
comment on column ex_tbl_1.decimal1 is '小数【1桁】';
comment on column ex_tbl_1.decimal3 is '小数【3桁】';
comment on column ex_tbl_1.dt is '日付';
comment on column ex_tbl_1.tm is '時刻';
comment on column ex_tbl_1.dtm is '日時';
comment on column ex_tbl_1.code2 is '区分値【2】';
comment on column ex_tbl_1.updated_at is '更新日時';
comment on column ex_tbl_1.created_at is '作成日時';
comment on column ex_tbl_1.lock_version is 'ロックバージョン';
