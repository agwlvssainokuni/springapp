SELECT
	C.id        AS id,
	C.rcpt_type AS rcpt_type,
	C.mail_addr AS mail_addr
FROM
	mail_templates AS A
	LEFT OUTER JOIN mail_template_addresses AS C
	ON
		C.mail_template_id = A.id
		AND
		C.deleted_flg = 0
WHERE
	A.name = :name
	AND
	A.deleted_flg = 0
ORDER BY
	C.id
