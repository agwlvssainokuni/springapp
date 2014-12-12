-- Project Name : SpringApp
-- Date/Time    : 2014/12/13 7:43:33
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 郵便番号マスタ
CREATE TABLE zipcd_master(
	id bigint NOT NULL auto_increment,
	city_cd INTEGER NOT NULL,
	zipcd VARCHAR (7) NOT NULL,
	pref VARCHAR (64) NOT NULL,
	city VARCHAR (64) NOT NULL,
	addr VARCHAR (64) NOT NULL,
	pref_kana VARCHAR (64) NOT NULL,
	city_kana VARCHAR (64) NOT NULL,
	addr_kana VARCHAR (64) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT zipcd_master_pkc PRIMARY KEY (id)
);

CREATE INDEX zipcd_master_ix1
	ON zipcd_master(zipcd);

COMMENT
	ON TABLE zipcd_master IS '郵便番号マスタ';

COMMENT
	ON COLUMN zipcd_master.id IS 'ID';

COMMENT
	ON COLUMN zipcd_master.city_cd IS '市区町村コード';

COMMENT
	ON COLUMN zipcd_master.zipcd IS '郵便番号';

COMMENT
	ON COLUMN zipcd_master.pref IS '都道府県';

COMMENT
	ON COLUMN zipcd_master.city IS '市区町村';

COMMENT
	ON COLUMN zipcd_master.addr IS '町域';

COMMENT
	ON COLUMN zipcd_master.pref_kana IS '都道府県(カナ)';

COMMENT
	ON COLUMN zipcd_master.city_kana IS '市区町村(カナ)';

COMMENT
	ON COLUMN zipcd_master.addr_kana IS '町域(カナ)';

COMMENT
	ON COLUMN zipcd_master.updated_at IS '更新日時';

COMMENT
	ON COLUMN zipcd_master.created_at IS '作成日時';

COMMENT
	ON COLUMN zipcd_master.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN zipcd_master.deleted_flg IS '削除フラグ';


