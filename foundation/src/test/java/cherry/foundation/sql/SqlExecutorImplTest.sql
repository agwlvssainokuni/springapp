-- 初期データ投入
INSERT INTO sql_execution (login) VALUES ('user1');
INSERT INTO sql_execution (login) VALUES ('user2');
INSERT INTO sql_execution (login) VALUES ('user3');

-- データ更新
UPDATE sql_execution SET name = login;
