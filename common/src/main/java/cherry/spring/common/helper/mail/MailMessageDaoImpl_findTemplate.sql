SELECT
	A.id        AS id,
	A.name      AS name,
	A.sender    AS sender,
	B.locale    AS locale,
	B.subject   AS subject,
	B.body      AS body
FROM
	mail_templates AS A
	JOIN mail_template_texts AS B
	ON
		B.mail_template_id = A.id
		AND
		B.locale = :locale
		AND
		B.deleted_flg = 0
WHERE
	A.name = :name
	AND
	A.deleted_flg = 0
