-- Project Name : SpringApp
-- Date/Time    : 2014/12/13 7:41:49
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 非同期実行状況管理・例外
CREATE TABLE async_process_exception(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	EXCEPTION text NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_exception_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_exception_ix1
	ON async_process_exception(async_id);

-- 非同期実行状況管理・コマンド・結果
CREATE TABLE async_process_command_result(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	exit_value INTEGER NOT NULL,
	stdout VARCHAR (1024) NOT NULL,
	stderr VARCHAR (1024) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_command_result_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_command_result_ix1
	ON async_process_command_result(async_id);

-- 非同期実行状況管理・コマンド・引数
CREATE TABLE async_process_command_arg(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	argument VARCHAR (50) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_command_arg_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_command_arg_ix1
	ON async_process_command_arg(async_id);

-- 非同期実行状況管理・コマンド
CREATE TABLE async_process_command(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	command VARCHAR (50) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_command_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_command_ix1
	ON async_process_command(async_id);

-- 非同期実行状況管理・ファイル処理・結果詳細
CREATE TABLE async_process_file_result_detail(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	record_number bigint NOT NULL,
	description VARCHAR (50),
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_file_result_detail_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_file_result_detail_ix1
	ON async_process_file_result_detail(async_id);

-- 非同期実行状況管理・ファイル処理・結果
CREATE TABLE async_process_file_result(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	total_count bigint NOT NULL,
	ok_count bigint NOT NULL,
	ng_count bigint NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_file_result_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_file_result_ix1
	ON async_process_file_result(async_id);

-- 非同期実行状況管理・ファイル処理・引数
CREATE TABLE async_process_file_arg(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	argument VARCHAR (50) NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_file_arg_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_file_arg_ix1
	ON async_process_file_arg(async_id);

-- 非同期実行状況管理・ファイル処理
CREATE TABLE async_process_file(
	id bigint NOT NULL auto_increment,
	async_id bigint NOT NULL,
	handler_name VARCHAR (50) NOT NULL,
	param_name VARCHAR (50) NOT NULL,
	original_filename VARCHAR (50) NOT NULL,
	content_type VARCHAR (50) NOT NULL,
	file_size bigint NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_file_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_file_ix1
	ON async_process_file(async_id);

-- 非同期実行状況管理
CREATE TABLE async_process(
	id bigint NOT NULL auto_increment,
	launched_by VARCHAR (32) NOT NULL,
	description VARCHAR (64) NOT NULL,
	async_type VARCHAR (3) NOT NULL,
	async_status VARCHAR (3) NOT NULL,
	registered_at TIMESTAMP NOT NULL,
	launched_at TIMESTAMP,
	started_at TIMESTAMP,
	finished_at TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_process_pkc PRIMARY KEY (id)
);

CREATE INDEX async_process_ix1
	ON async_process(launched_by);

COMMENT
	ON TABLE async_process_exception IS '非同期実行状況管理・例外';

COMMENT
	ON COLUMN async_process_exception.id IS 'ID';

COMMENT
	ON COLUMN async_process_exception.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_exception.EXCEPTION IS '例外情報';

COMMENT
	ON COLUMN async_process_exception.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_exception.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_exception.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_exception.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_command_result IS '非同期実行状況管理・コマンド・結果';

COMMENT
	ON COLUMN async_process_command_result.id IS 'ID';

COMMENT
	ON COLUMN async_process_command_result.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_command_result.exit_value IS '終了コード';

COMMENT
	ON COLUMN async_process_command_result.stdout IS '標準出力';

COMMENT
	ON COLUMN async_process_command_result.stderr IS '標準エラー出力';

COMMENT
	ON COLUMN async_process_command_result.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_command_result.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_command_result.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_command_result.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_command_arg IS '非同期実行状況管理・コマンド・引数';

COMMENT
	ON COLUMN async_process_command_arg.id IS 'ID';

COMMENT
	ON COLUMN async_process_command_arg.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_command_arg.argument IS '引数';

COMMENT
	ON COLUMN async_process_command_arg.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_command_arg.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_command_arg.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_command_arg.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_command IS '非同期実行状況管理・コマンド';

COMMENT
	ON COLUMN async_process_command.id IS 'ID';

COMMENT
	ON COLUMN async_process_command.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_command.command IS 'コマンド';

COMMENT
	ON COLUMN async_process_command.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_command.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_command.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_command.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_file_result_detail IS '非同期実行状況管理・ファイル処理・結果詳細';

COMMENT
	ON COLUMN async_process_file_result_detail.id IS 'ID';

COMMENT
	ON COLUMN async_process_file_result_detail.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_file_result_detail.record_number IS 'NGレコード番号';

COMMENT
	ON COLUMN async_process_file_result_detail.description IS '付加情報';

COMMENT
	ON COLUMN async_process_file_result_detail.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_file_result_detail.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_file_result_detail.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_file_result_detail.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_file_result IS '非同期実行状況管理・ファイル処理・結果';

COMMENT
	ON COLUMN async_process_file_result.id IS 'ID';

COMMENT
	ON COLUMN async_process_file_result.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_file_result.total_count IS '総件数';

COMMENT
	ON COLUMN async_process_file_result.ok_count IS 'OK件数';

COMMENT
	ON COLUMN async_process_file_result.ng_count IS 'NG件数';

COMMENT
	ON COLUMN async_process_file_result.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_file_result.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_file_result.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_file_result.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_file_arg IS '非同期実行状況管理・ファイル処理・引数';

COMMENT
	ON COLUMN async_process_file_arg.id IS 'ID';

COMMENT
	ON COLUMN async_process_file_arg.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_file_arg.argument IS '引数';

COMMENT
	ON COLUMN async_process_file_arg.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_file_arg.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_file_arg.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_file_arg.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process_file IS '非同期実行状況管理・ファイル処理';

COMMENT
	ON COLUMN async_process_file.id IS 'ID';

COMMENT
	ON COLUMN async_process_file.async_id IS '非同期実行状況管理ID';

COMMENT
	ON COLUMN async_process_file.handler_name IS 'ハンドラ名';

COMMENT
	ON COLUMN async_process_file.param_name IS 'パラメタ名';

COMMENT
	ON COLUMN async_process_file.original_filename IS '元ファイル名';

COMMENT
	ON COLUMN async_process_file.content_type IS 'コンテントタイプ';

COMMENT
	ON COLUMN async_process_file.file_size IS 'ファイルサイズ';

COMMENT
	ON COLUMN async_process_file.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process_file.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process_file.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process_file.deleted_flg IS '削除フラグ';

COMMENT
	ON TABLE async_process IS '非同期実行状況管理';

COMMENT
	ON COLUMN async_process.id IS 'ID';

COMMENT
	ON COLUMN async_process.launched_by IS 'キュー投入者ID';

COMMENT
	ON COLUMN async_process.description IS '内容表記';

COMMENT
	ON COLUMN async_process.async_type IS '非同期実行種別';

COMMENT
	ON COLUMN async_process.async_status IS '非同期実行状況';

COMMENT
	ON COLUMN async_process.registered_at IS '登録日時';

COMMENT
	ON COLUMN async_process.launched_at IS 'キュー投入日時';

COMMENT
	ON COLUMN async_process.started_at IS '実行開始日時';

COMMENT
	ON COLUMN async_process.finished_at IS '実行終了日時';

COMMENT
	ON COLUMN async_process.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_process.created_at IS '作成日時';

COMMENT
	ON COLUMN async_process.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_process.deleted_flg IS '削除フラグ';


