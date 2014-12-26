-- Project Name : SpringApp
-- Date/Time    : 2014/12/27 7:16:55
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 利用者
create table user (
  id BIGINT not null AUTO_INCREMENT
  , login_id VARCHAR(300) not null
  , password VARCHAR(100) not null
  , registered_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , first_name VARCHAR(50) not null
  , last_name VARCHAR(50) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint user_PKC primary key (id)
) ;

create unique index user_IX1
  on user(login_id);

-- 利用申請
create table signup_request (
  id BIGINT not null AUTO_INCREMENT
  , mail_addr VARCHAR(300) not null
  , token VARCHAR(50) not null
  , applied_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint signup_request_PKC primary key (id)
) ;

create index signup_request_IX1
  on signup_request(mail_addr);

create unique index signup_request_IX2
  on signup_request(token);

comment on table user is '利用者';
comment on column user.id is 'ID';
comment on column user.login_id is 'ログインID';
comment on column user.password is 'パスワード';
comment on column user.registered_at is '登録日時';
comment on column user.first_name is '氏名(名)';
comment on column user.last_name is '氏名(姓)';
comment on column user.updated_at is '更新日時';
comment on column user.created_at is '作成日時';
comment on column user.lock_version is 'ロックバージョン';
comment on column user.deleted_flg is '削除フラグ';

comment on table signup_request is '利用申請';
comment on column signup_request.id is 'ID';
comment on column signup_request.mail_addr is 'メールアドレス';
comment on column signup_request.token is 'トークン';
comment on column signup_request.applied_at is '申請日時';
comment on column signup_request.updated_at is '更新日時';
comment on column signup_request.created_at is '作成日時';
comment on column signup_request.lock_version is 'ロックバージョン';
comment on column signup_request.deleted_flg is '削除フラグ';
