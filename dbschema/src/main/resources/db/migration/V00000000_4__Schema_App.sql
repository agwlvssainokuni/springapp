-- Project Name : SpringApp
-- Date/Time    : 2014/08/24 20:14:45
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 利用者
CREATE TABLE USER (
	id INTEGER NOT NULL auto_increment,
	login_id VARCHAR (512) NOT NULL,
	password CHAR (60) NOT NULL,
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	first_name VARCHAR (64) NOT NULL,
	last_name VARCHAR (64) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT user_pkc PRIMARY KEY (id)
);

CREATE UNIQUE INDEX user_ix1
	ON USER (login_id);

-- 利用申請
CREATE TABLE signup_request(
	id INTEGER NOT NULL auto_increment,
	mail_addr VARCHAR (512) NOT NULL,
	token CHAR (36) NOT NULL,
	applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT signup_request_pkc PRIMARY KEY (id)
);

CREATE INDEX signup_request_ix1
	ON signup_request(mail_addr);

CREATE UNIQUE INDEX signup_request_ix2
	ON signup_request(token);

COMMENT
	ON TABLE USER IS '利用者';

COMMENT
	ON COLUMN USER.id IS 'ID';

COMMENT
	ON COLUMN USER.login_id IS 'ログインID';

COMMENT
	ON COLUMN USER.password IS 'パスワード';

COMMENT
	ON COLUMN USER.registered_at IS '登録日時';

COMMENT
	ON COLUMN USER.first_name IS '氏名(名)';

COMMENT
	ON COLUMN USER.last_name IS '氏名(姓)';

COMMENT
	ON COLUMN USER.updated_at IS '更新日時';

COMMENT
	ON COLUMN USER.created_at IS '作成日時';

COMMENT
	ON COLUMN USER.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN USER.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE signup_request IS '利用申請';

COMMENT
	ON COLUMN signup_request.id IS 'ID';

COMMENT
	ON COLUMN signup_request.mail_addr IS 'メールアドレス';

COMMENT
	ON COLUMN signup_request.token IS 'トークン';

COMMENT
	ON COLUMN signup_request.applied_at IS '申請日時';

COMMENT
	ON COLUMN signup_request.updated_at IS '更新日時';

COMMENT
	ON COLUMN signup_request.created_at IS '作成日時';

COMMENT
	ON COLUMN signup_request.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN signup_request.deleted_flg IS '削除フラグ';


