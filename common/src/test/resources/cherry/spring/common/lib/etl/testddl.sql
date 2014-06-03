CREATE TABLE member (
	id		INTEGER		NOT NULL	auto_increment,
	name	VARCHAR(32)	NOT NULL,
	address	VARCHAR(64)	NOT NULL,
	CONSTRAINT member_pkc PRIMARY KEY (id)
);
