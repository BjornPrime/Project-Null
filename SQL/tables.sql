CREATE TABLE bank_users (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	email VARCHAR(30),
	password VARCHAR(30)
);

CREATE TABLE bank_accounts (
	id SERIAL PRIMARY KEY,
	account_type VARCHAR(10),
	balance INTEGER
);

CREATE TABLE account_user_junctions (
	junction_id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES bank_users (id) NOT NULL,
	account_id INTEGER REFERENCES bank_accounts (id) NOT NULL
);

