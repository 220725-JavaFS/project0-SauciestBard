--Setup tables

DROP TABLE IF EXISTS ban_state CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS ban_list CASCADE;

CREATE TABLE user_roles(
	role_id integer PRIMARY KEY NOT NULL,
	role_name varchar (11) NOT NULL
);

INSERT INTO user_roles(role_id,role_name) VALUES
	(0,'user'),
	(1, 'moderator'),
	(2, 'admin');

CREATE TABLE ban_state(
	state_id integer PRIMARY KEY NOT NULL,
	state_name varchar (10) NOT NULL
);

INSERT INTO ban_state(state_id, state_name) VALUES
	(0, 'released'),
	(1, 'timeout'),
	(2, 'permanent');


CREATE TABLE users(
	user_name varchar PRIMARY KEY NOT NULL,
	password_hash varchar NOT NULL,
	user_role integer REFERENCES user_roles(role_id) NOT NULL
);


CREATE TABLE messages(
	message_id SERIAL PRIMARY KEY NOT NULL,
	sender_id varchar REFERENCES users(user_name) NOT NULL,
	sent timestamp NOT NULL,
	message varchar NOT NULL
);



CREATE TABLE ban_list(
	ban_id SERIAL PRIMARY KEY NOT NULL,
	banned_user_name varchar REFERENCES users(user_name) NOT NULL,
	banning_mod varchar REFERENCES users(user_name) NOT NULL,
	ban_reason varchar NOT NULL,
	ban_state integer REFERENCES ban_state(state_id) NOT NULL,
	ban_release timestamp
);