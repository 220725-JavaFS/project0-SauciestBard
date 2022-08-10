--adds 3 test users

INSERT INTO users (user_name, password_hash, user_role) VALUES (
	('user',3433489, 0),
	('mod', 106438209, 0),
	('admin', 106438208, 2)
)

--login info
--user, pass
--mod, pass1
--admin, pass2