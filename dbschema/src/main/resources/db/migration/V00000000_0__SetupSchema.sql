-- Project Name : SpringApp
-- Date/Time    : 2014/04/23 1:11:04
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 非同期処理
CREATE TABLE async_procs( 
	id INTEGER NOT NULL auto_increment, 
	name VARCHAR (32) NOT NULL, 
	status VARCHAR (32) NOT NULL CHECK status IN ( 
		'PREPARING', 
		'INVOKED', 
		'PROCESSING', 
		'SUCCESS', 
		'ERROR'
	) , 
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	invoked_at TIMESTAMP, 
	started_at TIMESTAMP, 
	finished_at TIMESTAMP, 
	RESULT VARCHAR (4096), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT async_procs_pkc PRIMARY KEY (id)
); 

-- メールテンプレート文面
CREATE TABLE mail_template_texts( 
	id INTEGER NOT NULL auto_increment, 
	mail_template_id INTEGER NOT NULL, 
	locale VARCHAR (5) NOT NULL, 
	subject VARCHAR (1024) NOT NULL, 
	body VARCHAR (4096) NOT NULL, 
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
	mail_addr VARCHAR (512) NOT NULL, 
	rcpt_type VARCHAR (3) NOT NULL CHECK rcpt_type IN ('CC', 'BCC'), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT mail_template_addresses_pkc PRIMARY KEY (id)
); 

CREATE UNIQUE INDEX mail_template_addresses_ix1 
	ON mail_template_addresses(mail_template_id, mail_addr, rcpt_type); 

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

CREATE UNIQUE INDEX mail_templates_ix1 
	ON mail_templates(name); 

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

-- 利用者
CREATE TABLE users( 
	id INTEGER NOT NULL auto_increment, 
	mail_addr VARCHAR (512) NOT NULL, 
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
	ON users(mail_addr); 

COMMENT 
	ON TABLE async_procs IS '非同期処理'; 

COMMENT 
	ON COLUMN async_procs.id IS 'ID'; 

COMMENT 
	ON COLUMN async_procs.name IS '処理名称'; 

COMMENT 
	ON COLUMN async_procs.status IS '状況'; 

COMMENT 
	ON COLUMN async_procs.registered_at IS '登録日時'; 

COMMENT 
	ON COLUMN async_procs.invoked_at IS '投入日時'; 

COMMENT 
	ON COLUMN async_procs.started_at IS '開始日時'; 

COMMENT 
	ON COLUMN async_procs.finished_at IS '終了日時'; 

COMMENT 
	ON COLUMN async_procs.RESULT IS '結果情報'; 

COMMENT 
	ON COLUMN async_procs.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN async_procs.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN async_procs.deleted_flg IS '削除フラグ'; 

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
	ON COLUMN mail_template_addresses.mail_addr IS 'メールアドレス'; 

COMMENT 
	ON COLUMN mail_template_addresses.rcpt_type IS '宛先区分'; 

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

COMMENT 
	ON TABLE users IS '利用者'; 

COMMENT 
	ON COLUMN users.id IS 'ID'; 

COMMENT 
	ON COLUMN users.mail_addr IS 'メールアドレス'; 

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


