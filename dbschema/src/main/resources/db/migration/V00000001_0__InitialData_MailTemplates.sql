INSERT INTO mail_templates (
	name,
	sender
)
SELECT
	A.name,
	A.sender
FROM
	CSVREAD('classpath:/db/migration/V00000001_0__mail_templates.csv') AS A
;

INSERT INTO mail_template_addresses (
	mail_template_id,
	mail_addr,
	rcpt_type
)
SELECT
	B.id,
	A.mail_addr,
	A.rcpt_type
FROM
	CSVREAD('classpath:/db/migration/V00000001_0__mail_template_addresses.csv') AS A
	JOIN mail_templates AS B
	ON
		B.name = A.name
;

INSERT INTO mail_template_texts (
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
	CSVREAD('classpath:/db/migration/V00000001_0__mail_template_texts.csv') AS A
	JOIN mail_templates AS B
	ON
		B.name = A.name
;
