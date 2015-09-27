-- NAME: selectAll
SELECT
	id,
	str,
	int32,
	int64,
	bigint,
	bigdec
FROM
	verify_secure
;

-- NAME: insert
INSERT INTO verify_secure (
	str,
	int32,
	int64,
	bigint,
	bigdec
) VALUES (
	:str,
	:int32,
	:int64,
	:bigint,
	:bigdec
)
;
