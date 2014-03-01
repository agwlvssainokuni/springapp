-- Project Name : SpringApp
-- Date/Time    : 2014/03/01 10:28:58
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 利用申請
CREATE TABLE signup_entries( 
	id INTEGER NOT NULL auto_increment, 
	mail_address VARCHAR (512) NOT NULL, 
	token CHAR (36) NOT NULL, 
	applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT signup_entries_pkc PRIMARY KEY (id)
); 

CREATE INDEX signup_entries_ix1 
	ON signup_entries(mail_address); 

CREATE UNIQUE INDEX signup_entries_ix2 
	ON signup_entries(token); 

-- 利用者
CREATE TABLE users( 
	id INTEGER NOT NULL auto_increment, 
	mail_address VARCHAR (512) NOT NULL, 
	password CHAR (60) NOT NULL, 
	applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	first_name VARCHAR (32) NOT NULL, 
	last_name VARCHAR (32) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT users_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX users_ix1 
	ON users(mail_address); 

COMMENT 
	ON TABLE signup_entries IS '利用申請'; 

COMMENT 
	ON COLUMN signup_entries.id IS 'ID'; 

COMMENT 
	ON COLUMN signup_entries.mail_address IS 'メールアドレス'; 

COMMENT 
	ON COLUMN signup_entries.token IS 'トークン'; 

COMMENT 
	ON COLUMN signup_entries.applied_at IS '申請日時'; 

COMMENT 
	ON COLUMN signup_entries.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN signup_entries.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN signup_entries.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE users IS '利用者'; 

COMMENT 
	ON COLUMN users.id IS 'ID'; 

COMMENT 
	ON COLUMN users.mail_address IS 'メールアドレス'; 

COMMENT 
	ON COLUMN users.password IS 'パスワード'; 

COMMENT 
	ON COLUMN users.applied_at IS '申請日時'; 

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


