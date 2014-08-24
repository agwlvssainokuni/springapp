INSERT INTO mail_template (
	name,
	sender
)
SELECT
	A.name,
	A.sender
FROM
	CSVREAD('classpath:/db/migration/V00000001_0__mail_template.csv') AS A
;

INSERT INTO mail_template_address (
	mail_template_id,
	mail_addr,
	rcpt_type
)
SELECT
	B.id,
	A.mail_addr,
	A.rcpt_type
FROM
	CSVREAD('classpath:/db/migration/V00000001_0__mail_template_address.csv') AS A
	JOIN mail_template AS B
	ON
		B.name = A.name
;

INSERT INTO mail_template_text (
	mail_template_id,
	locale,
	subject,
	body
)
SELECT
	B.id,
	A.locale,
	A.subject,
	A.body
FROM
	CSVREAD('classpath:/db/migration/V00000001_0__mail_template_text.csv') AS A
	JOIN mail_template AS B
	ON
		B.name = A.name
;
