-- Project Name : SpringApp
-- Date/Time    : 2014/06/15 19:36:17
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

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


