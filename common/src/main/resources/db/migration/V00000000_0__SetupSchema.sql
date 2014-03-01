-- Project Name : SpringApp
-- Date/Time    : 2014/03/01 11:02:14
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- メールテンプレート文面
CREATE TABLE mail_template_texts( 
	id INTEGER NOT NULL auto_increment, 
	mail_template_id INTEGER NOT NULL, 
	locale VARCHAR (5) NOT NULL, 
	subject VARCHAR (1024) NOT NULL, 
	body VARCHAR (4098) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT mail_template_texts_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX mail_template_texts_ix1 
	ON mail_template_texts(mail_template_id, locale); 

-- メールテンプレート宛先
CREATE TABLE mail_template_addresses( 
	id INTEGER NOT NULL auto_increment, 
	mail_template_id INTEGER NOT NULL, 
	mail_address VARCHAR (512) NOT NULL, 
	type VARCHAR (3) NOT NULL CHECK type IN ('CC', 'BCC'), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT mail_template_addresses_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX mail_template_addresses_ix1 
	ON mail_template_addresses(mail_address, type); 

CREATE INDEX mail_template_addresses_ix2 
	ON mail_template_addresses(mail_template_id); 

-- メールテンプレート
CREATE TABLE mail_templates( 
	id INTEGER NOT NULL auto_increment, 
	name VARCHAR (32) NOT NULL, 
	sender VARCHAR (512) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT mail_templates_pkc PRIMARY KEY (id)
); 

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
	first_name VARCHAR (64) NOT NULL, 
	last_name VARCHAR (64) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT users_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX users_ix1 
	ON users(mail_address); 

COMMENT 
	ON TABLE mail_template_texts IS 'メールテンプレート文面'; 

COMMENT 
	ON COLUMN mail_template_texts.id IS 'ID'; 

COMMENT 
	ON COLUMN mail_template_texts.mail_template_id IS 'メールテンプレートID'; 

COMMENT 
	ON COLUMN mail_template_texts.locale IS 'ロケール'; 

COMMENT 
	ON COLUMN mail_template_texts.subject IS '件名'; 

COMMENT 
	ON COLUMN mail_template_texts.body IS '本文'; 

COMMENT 
	ON COLUMN mail_template_texts.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN mail_template_texts.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN mail_template_texts.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE mail_template_addresses IS 'メールテンプレート宛先'; 

COMMENT 
	ON COLUMN mail_template_addresses.id IS 'ID'; 

COMMENT 
	ON COLUMN mail_template_addresses.mail_template_id IS 'メールテンプレートID'; 

COMMENT 
	ON COLUMN mail_template_addresses.mail_address IS 'メールアドレス'; 

COMMENT 
	ON COLUMN mail_template_addresses.type IS '宛先区分'; 

COMMENT 
	ON COLUMN mail_template_addresses.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN mail_template_addresses.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN mail_template_addresses.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE mail_templates IS 'メールテンプレート'; 

COMMENT 
	ON COLUMN mail_templates.id IS 'ID'; 

COMMENT 
	ON COLUMN mail_templates.name IS 'テンプレート名'; 

COMMENT 
	ON COLUMN mail_templates.sender IS '差出人'; 

COMMENT 
	ON COLUMN mail_templates.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN mail_templates.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN mail_templates.deleted_flg IS '削除フラグ'; 

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


