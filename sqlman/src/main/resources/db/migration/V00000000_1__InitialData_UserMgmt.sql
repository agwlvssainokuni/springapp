-- 利用者データ検索
INSERT INTO sql_metadata (sql_type, name, description, owned_by, published_flg, registered_at, changed_at) VALUES (
'clause', '利用者データ検索', '利用者データを検索する。', 'administrator', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO sql_clause (id, database_name, select_clause, from_clause, order_by_clause) VALUES (
(SELECT id FROM sql_metadata WHERE name = '利用者データ検索'),
'system', '*', 'user_account', 'id ASC'
);

-- 利用者データ一括登録(PWあり)
INSERT INTO sql_metadata (sql_type, name, description, owned_by, published_flg, registered_at, changed_at) VALUES (
'load', '利用者データ一括登録(PWあり)', '利用者データを一括登録する。パスワードを設定する。', 'administrator', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO sql_load (id, database_name, query) VALUES (
(SELECT id FROM sql_metadata WHERE name = '利用者データ一括登録(PWあり)'),
'system', 'INSERT INTO user_account (mail_addr, password) VALUES (:mailAddr, :password)'
);

-- 利用者データ一括登録(PWなし)
INSERT INTO sql_metadata (sql_type, name, description, owned_by, published_flg, registered_at, changed_at) VALUES (
'load', '利用者データ一括登録(PWなし)', '利用者データを一括登録する。パスワードは設定しない。', 'administrator', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO sql_load (id, database_name, query) VALUES (
(SELECT id FROM sql_metadata WHERE name = '利用者データ一括登録(PWなし)'),
'system', 'INSERT INTO user_account (mail_addr,password) VALUES (:mailAddr,'''')'
);

-- 利用者データ一括削除
INSERT INTO sql_metadata (sql_type, name, description, owned_by, published_flg, registered_at, changed_at) VALUES (
'load', '利用者データ一括削除', '利用者データを一括削除する。', 'administrator', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO sql_load (id, database_name, query) VALUES (
(SELECT id FROM sql_metadata WHERE name = '利用者データ一括削除'),
'system', 'DELETE FROM user_account WHERE mail_addr = :mailAddr'
);
