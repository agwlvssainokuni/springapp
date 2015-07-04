-- Project Name : SqlApp
-- Date/Time    : 2015/07/04 19:53:52
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- SQL管理機能・CSV取込み
create table sql_load (
  id INTEGER not null AUTO_INCREMENT
  , database_name VARCHAR(50) default 'default' not null
  , query VARCHAR(5000) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint sql_load_PKC primary key (id)
) ;

-- SQL管理機能・ステートメント指定
create table sql_statement (
  id INTEGER not null AUTO_INCREMENT
  , database_name VARCHAR(50) default 'default' not null
  , query VARCHAR(5000) not null
  , param_map VARCHAR(5000)
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint sql_statement_PKC primary key (id)
) ;

-- SQL管理機能・句指定
create table sql_clause (
  id INTEGER not null AUTO_INCREMENT
  , database_name VARCHAR(50) default 'default' not null
  , select_clause VARCHAR(500) not null
  , from_clause VARCHAR(500) not null
  , where_clause VARCHAR(500)
  , group_by_clause VARCHAR(500)
  , having_clause VARCHAR(500)
  , order_by_clause VARCHAR(500)
  , param_map VARCHAR(5000)
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint sql_clause_PKC primary key (id)
) ;

-- SQL管理機能・メタデータ
create table sql_metadata (
  id INTEGER not null AUTO_INCREMENT
  , sql_type VARCHAR(32) not null
  , name VARCHAR(50) not null
  , description VARCHAR(500) not null
  , owned_by VARCHAR(32) not null
  , published_flg INTEGER default 0 not null
  , registered_at TIMESTAMP not null
  , changed_at TIMESTAMP not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint sql_metadata_PKC primary key (id)
) ;

comment on table sql_load is 'SQL管理機能・CSV取込み';
comment on column sql_load.id is 'ID';
comment on column sql_load.database_name is 'DB名';
comment on column sql_load.query is 'クエリ';
comment on column sql_load.updated_at is '更新日時';
comment on column sql_load.created_at is '作成日時';
comment on column sql_load.lock_version is 'ロックバージョン';

comment on table sql_statement is 'SQL管理機能・ステートメント指定';
comment on column sql_statement.id is 'ID';
comment on column sql_statement.database_name is 'DB名';
comment on column sql_statement.query is 'クエリ';
comment on column sql_statement.param_map is 'パラメタMAP(JSON)';
comment on column sql_statement.updated_at is '更新日時';
comment on column sql_statement.created_at is '作成日時';
comment on column sql_statement.lock_version is 'ロックバージョン';

comment on table sql_clause is 'SQL管理機能・句指定';
comment on column sql_clause.id is 'ID';
comment on column sql_clause.database_name is 'DB名';
comment on column sql_clause.select_clause is 'SELECT句';
comment on column sql_clause.from_clause is 'FROM句';
comment on column sql_clause.where_clause is 'WHERE句';
comment on column sql_clause.group_by_clause is 'GROUP BY句';
comment on column sql_clause.having_clause is 'HAVING句';
comment on column sql_clause.order_by_clause is 'ORDER BY句';
comment on column sql_clause.param_map is 'パラメタMAP(JSON)';
comment on column sql_clause.updated_at is '更新日時';
comment on column sql_clause.created_at is '作成日時';
comment on column sql_clause.lock_version is 'ロックバージョン';

comment on table sql_metadata is 'SQL管理機能・メタデータ';
comment on column sql_metadata.id is 'ID';
comment on column sql_metadata.sql_type is 'SQL種別';
comment on column sql_metadata.name is '表示名';
comment on column sql_metadata.description is '説明文';
comment on column sql_metadata.owned_by is '所有者';
comment on column sql_metadata.published_flg is '公開フラグ';
comment on column sql_metadata.registered_at is '登録日時';
comment on column sql_metadata.changed_at is '変更日時';
comment on column sql_metadata.updated_at is '更新日時';
comment on column sql_metadata.created_at is '作成日時';
comment on column sql_metadata.lock_version is 'ロックバージョン';
