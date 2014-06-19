-- Project Name : SpringApp
-- Date/Time    : 2014/06/15 19:37:44
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 非同期処理
CREATE TABLE async_procs( 
	id INTEGER NOT NULL auto_increment, 
	launcher_id VARCHAR (512) NOT NULL, 
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

CREATE INDEX async_procs_ix1 
	ON async_procs(launcher_id); 

COMMENT 
	ON TABLE async_procs IS '非同期処理'; 

COMMENT 
	ON COLUMN async_procs.id IS 'ID'; 

COMMENT 
	ON COLUMN async_procs.launcher_id IS '起動者ID'; 

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

