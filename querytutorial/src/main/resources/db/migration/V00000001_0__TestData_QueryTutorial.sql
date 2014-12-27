INSERT INTO priority_master (
	priority_cd,
	priority_label
)
VALUES
(0, '優先度：指定なし'),
(1, '優先度：高'),
(2, '優先度：中'),
(3, '優先度：小');

INSERT INTO todo (
	posted_by,
	posted_at,
	due_dt,
	done_at,
	done_flg,
	priority_cd,
	description
)
VALUES
('user01', '2015-01-01 00:00:00', '2015-01-07', '2015-01-07 00:00:00', 1, 1, '説明文A'),
('user01', '2015-01-02 00:00:00', '2015-01-07', '2015-01-08 00:00:00', 1, 2, '説明文B'),
('user01', '2015-01-03 00:00:00', '2015-01-07', NULL, 0, 3, '説明文C'),
('user02', '2015-02-01 00:00:00', '2015-02-07', '2015-02-07 00:00:00', 1, 2, '説明文D'),
('user02', '2015-02-02 00:00:00', '2015-02-07', NULL, 0, 3, '説明文E')
;