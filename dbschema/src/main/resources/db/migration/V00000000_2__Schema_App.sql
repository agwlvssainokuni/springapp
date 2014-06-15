-- Project Name : SpringApp
-- Date/Time    : 2014/06/15 19:38:52
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 利用者
CREATE TABLE users( 
	id INTEGER NOT NULL auto_increment, 
	login_id VARCHAR (512) NOT NULL, 
	password CHAR (60) NOT NULL, 
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	first_name VARCHAR (64) NOT NULL, 
	last_name VARCHAR (64) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT users_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX users_ix1 
	ON users(login_id); 

-- 利用申請
CREATE TABLE signup_requests( 
	id INTEGER NOT NULL auto_increment, 
	mail_addr VARCHAR (512) NOT NULL, 
	token CHAR (36) NOT NULL, 
	applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT signup_requests_pkc PRIMARY KEY (id)
); 

CREATE INDEX signup_requests_ix1 
	ON signup_requests(mail_addr); 

CREATE UNIQUE INDEX signup_requests_ix2 
	ON signup_requests(token); 

COMMENT 
	ON TABLE users IS '利用者'; 

COMMENT 
	ON COLUMN users.id IS 'ID'; 

COMMENT 
	ON COLUMN users.login_id IS 'ログインID'; 

COMMENT 
	ON COLUMN users.password IS 'パスワード'; 

COMMENT 
	ON COLUMN users.registered_at IS '登録日時'; 

COMMENT 
	ON COLUMN users.first_name IS '氏名(名)'; 

COMMENT 
	ON COLUMN users.last_name IS '氏名(姓)'; 

COMMENT 
	ON COLUMN users.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN users.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN users.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE signup_requests IS '利用申請'; 

COMMENT 
	ON COLUMN signup_requests.id IS 'ID'; 

COMMENT 
	ON COLUMN signup_requests.mail_addr IS 'メールアドレス'; 

COMMENT 
	ON COLUMN signup_requests.token IS 'トークン'; 

COMMENT 
	ON COLUMN signup_requests.applied_at IS '申請日時'; 

COMMENT 
	ON COLUMN signup_requests.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN signup_requests.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN signup_requests.deleted_flg IS '削除フラグ'; 


