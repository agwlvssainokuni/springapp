-- Project Name : SpringApp
-- Date/Time    : 2014/12/27 6:24:19
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 郵便番号マスタ
create table zipcd_master (
  id BIGINT not null AUTO_INCREMENT
  , city_cd INTEGER not null
  , zipcd VARCHAR(7) not null
  , pref VARCHAR(64) not null
  , city VARCHAR(64) not null
  , addr VARCHAR(64) not null
  , pref_kana VARCHAR(64) not null
  , city_kana VARCHAR(64) not null
  , addr_kana VARCHAR(64) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint zipcd_master_PKC primary key (id)
) ;

create index zipcd_master_IX1
  on zipcd_master(zipcd);

comment on table zipcd_master is '郵便番号マスタ';
comment on column zipcd_master.id is 'ID';
comment on column zipcd_master.city_cd is '市区町村コード';
comment on column zipcd_master.zipcd is '郵便番号';
comment on column zipcd_master.pref is '都道府県';
comment on column zipcd_master.city is '市区町村';
comment on column zipcd_master.addr is '町域';
comment on column zipcd_master.pref_kana is '都道府県(カナ)';
comment on column zipcd_master.city_kana is '市区町村(カナ)';
comment on column zipcd_master.addr_kana is '町域(カナ)';
comment on column zipcd_master.updated_at is '更新日時';
comment on column zipcd_master.created_at is '作成日時';
comment on column zipcd_master.lock_version is 'ロックバージョン';
comment on column zipcd_master.deleted_flg is '削除フラグ';
