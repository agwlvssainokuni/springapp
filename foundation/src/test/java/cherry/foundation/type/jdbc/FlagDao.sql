-- NAME: selectAll
SELECT
	id,
	flag_code,
	deleted_flg
FROM
	verify_flag
;

-- NAME: insert
INSERT INTO verify_flag (
	flag_code,
	deleted_flg
) VALUES (
	:flagCode,
	:deletedFlg
)
;
