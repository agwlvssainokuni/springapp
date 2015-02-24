INSERT INTO digit (d)
VALUES
(0),
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9)
;

INSERT INTO holiday_master (dt)
SELECT
	DATEADD('DAY', A.d * 100 + B.d * 10 + C.d, CURRENT_DATE)
FROM
	digit A, digit B, digit C
WHERE
	DAY_OF_WEEK(DATEADD('DAY', A.d * 100 + B.d * 10 + C.d, CURRENT_DATE)) IN (1, 7)
;
