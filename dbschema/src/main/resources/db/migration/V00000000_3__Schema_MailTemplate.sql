-- Project Name : SpringApp
-- Date/Time    : 2014/08/24 20:13:41
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- メールテンプレート
CREATE TABLE mail_template(
	id INTEGER NOT NULL auto_increment,
	name VARCHAR (32) NOT NULL,
	sender VARCHAR (512) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT mail_template_pkc PRIMARY KEY (id)
);

CREATE UNIQUE INDEX mail_template_ix1
	ON mail_template(name);

-- メールテンプレート宛先
CREATE TABLE mail_template_address(
	id INTEGER NOT NULL auto_increment,
	mail_template_id INTEGER NOT NULL,
	mail_addr VARCHAR (512) NOT NULL,
	rcpt_type VARCHAR (3) NOT NULL CHECK rcpt_type IN ('CC', 'BCC'),
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT mail_template_address_pkc PRIMARY KEY (id)
);

CREATE UNIQUE INDEX mail_template_address_ix1
	ON mail_template_address(mail_template_id, mail_addr, rcpt_type);

-- メールテンプレート文面
CREATE TABLE mail_template_text(
	id INTEGER NOT NULL auto_increment,
	mail_template_id INTEGER NOT NULL,
	locale VARCHAR (5) NOT NULL,
	subject VARCHAR (1024) NOT NULL,
	body VARCHAR (4096) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT mail_template_text_pkc PRIMARY KEY (id)
);

CREATE UNIQUE INDEX mail_template_text_ix1
	ON mail_template_text(mail_template_id, locale);

COMMENT
	ON TABLE mail_template IS 'メールテンプレート';

COMMENT
	ON COLUMN mail_template.id IS 'ID';

COMMENT
	ON COLUMN mail_template.name IS 'テンプレート名';

COMMENT
	ON COLUMN mail_template.sender IS '差出人';

COMMENT
	ON COLUMN mail_template.updated_at IS '更新日時';

COMMENT
	ON COLUMN mail_template.created_at IS '作成日時';

COMMENT
	ON COLUMN mail_template.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN mail_template.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE mail_template_address IS 'メールテンプレート宛先';

COMMENT
	ON COLUMN mail_template_address.id IS 'ID';

COMMENT
	ON COLUMN mail_template_address.mail_template_id IS 'メールテンプレートID';

COMMENT
	ON COLUMN mail_template_address.mail_addr IS 'メールアドレス';

COMMENT
	ON COLUMN mail_template_address.rcpt_type IS '宛先区分';

COMMENT
	ON COLUMN mail_template_address.updated_at IS '更新日時';

COMMENT
	ON COLUMN mail_template_address.created_at IS '作成日時';

COMMENT
	ON COLUMN mail_template_address.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN mail_template_address.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE mail_template_text IS 'メールテンプレート文面';

COMMENT
	ON COLUMN mail_template_text.id IS 'ID';

COMMENT
	ON COLUMN mail_template_text.mail_template_id IS 'メールテンプレートID';

COMMENT
	ON COLUMN mail_template_text.locale IS 'ロケール';

COMMENT
	ON COLUMN mail_template_text.subject IS '件名';

COMMENT
	ON COLUMN mail_template_text.body IS '本文';

COMMENT
	ON COLUMN mail_template_text.updated_at IS '更新日時';

COMMENT
	ON COLUMN mail_template_text.created_at IS '作成日時';

COMMENT
	ON COLUMN mail_template_text.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN mail_template_text.deleted_flg IS '削除フラグ';


