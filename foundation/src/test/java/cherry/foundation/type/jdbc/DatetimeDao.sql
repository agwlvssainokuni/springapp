-- NAME: selectAll
SELECT
	id,
	dt,
	tm,
	dtm
FROM
	verify_datetime
;

-- NAME: insert
INSERT INTO verify_datetime (
	dt,
	tm,
	dtm
) VALUES (
	:dt,
	:tm,
	:dtm
)
;
