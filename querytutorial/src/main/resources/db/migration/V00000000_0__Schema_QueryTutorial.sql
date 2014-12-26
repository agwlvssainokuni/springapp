-- Project Name : QueryTutorial
-- Date/Time    : 2014/12/27 8:01:25
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- ノート
create table note (
  id BIGINT not null AUTO_INCREMENT
  , todo_id BIGINT not null
  , posted_by VARCHAR(100) not null
  , posted_at TIMESTAMP not null
  , description VARCHAR(1000) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 0 not null
  , deleted_flg INTEGER default 0 not null
  , constraint note_PKC primary key (id)
) ;

create index note_IX1
  on note(todo_id);

create index note_IX2
  on note(posted_by);

-- 優先度マスタ
create table priority_master (
  id BIGINT not null AUTO_INCREMENT
  , priority_cd INTEGER not null
  , priority_label VARCHAR(10) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 0 not null
  , deleted_flg INTEGER default 0 not null
  , constraint priority_master_PKC primary key (id)
) ;

create index priority_master_IX1
  on priority_master(priority_cd);

-- TODO
create table todo (
  id BIGINT not null AUTO_INCREMENT
  , posted_by VARCHAR(100) not null
  , posted_at TIMESTAMP not null
  , due_dt DATE not null
  , done_at TIMESTAMP
  , done_flg INTEGER default 0 not null
  , priority_cd INTEGER default 0 not null
  , description VARCHAR(1000) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 0 not null
  , deleted_flg INTEGER default 0 not null
  , constraint todo_PKC primary key (id)
) ;

create index todo_IX1
  on todo(posted_by);

comment on table note is 'ノート';
comment on column note.id is 'ID';
comment on column note.todo_id is 'TODO ID';
comment on column note.posted_by is '投稿者';
comment on column note.posted_at is '投稿日時';
comment on column note.description is 'ノート';
comment on column note.updated_at is '更新日時';
comment on column note.created_at is '作成日時';
comment on column note.lock_version is 'ロックバージョン';
comment on column note.deleted_flg is '削除フラグ';

comment on table priority_master is '優先度マスタ';
comment on column priority_master.id is 'ID';
comment on column priority_master.priority_cd is '優先度コード';
comment on column priority_master.priority_label is '優先度表示名';
comment on column priority_master.updated_at is '更新日時';
comment on column priority_master.created_at is '作成日時';
comment on column priority_master.lock_version is 'ロックバージョン';
comment on column priority_master.deleted_flg is '削除フラグ';

comment on table todo is 'TODO';
comment on column todo.id is 'ID';
comment on column todo.posted_by is '投稿者';
comment on column todo.posted_at is '登校日時';
comment on column todo.due_dt is '期日';
comment on column todo.done_at is '完了日時';
comment on column todo.done_flg is '完了フラグ';
comment on column todo.priority_cd is '優先度コード';
comment on column todo.description is 'TODO内容';
comment on column todo.updated_at is '更新日時';
comment on column todo.created_at is '作成日時';
comment on column todo.lock_version is 'ロックバージョン';
comment on column todo.deleted_flg is '削除フラグ';
