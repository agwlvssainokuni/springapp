-- Project Name : QueryTutorial
-- Date/Time    : 2014/12/31 6:47:30
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 投稿者
create table author (
  id BIGINT not null AUTO_INCREMENT
  , login_id VARCHAR(100) not null
  , name VARCHAR(100) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 0 not null
  , deleted_flg INTEGER default 0 not null
  , constraint author_PKC primary key (id)
) ;

create index author_IX1
  on author(login_id);

-- TODO
create table todo (
  id BIGINT not null AUTO_INCREMENT
  , posted_by VARCHAR(100) not null
  , posted_at TIMESTAMP not null
  , due_dt DATE not null
  , done_at TIMESTAMP
  , done_flg INTEGER default 0 not null
  , description VARCHAR(1000) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 0 not null
  , deleted_flg INTEGER default 0 not null
  , constraint todo_PKC primary key (id)
) ;

create index todo_IX1
  on todo(posted_by);

comment on table author is '投稿者';
comment on column author.id is 'ID';
comment on column author.login_id is 'ログインID';
comment on column author.name is '投稿者名';
comment on column author.updated_at is '更新日時';
comment on column author.created_at is '作成日時';
comment on column author.lock_version is 'ロックバージョン';
comment on column author.deleted_flg is '削除フラグ';

comment on table todo is 'TODO';
comment on column todo.id is 'ID';
comment on column todo.posted_by is '投稿者';
comment on column todo.posted_at is '登校日時';
comment on column todo.due_dt is '期日';
comment on column todo.done_at is '完了日時';
comment on column todo.done_flg is '完了フラグ';
comment on column todo.description is 'TODO内容';
comment on column todo.updated_at is '更新日時';
comment on column todo.created_at is '作成日時';
comment on column todo.lock_version is 'ロックバージョン';
comment on column todo.deleted_flg is '削除フラグ';
