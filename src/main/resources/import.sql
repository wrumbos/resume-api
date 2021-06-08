INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO users(email, password, username) VALUES('wcode@wcode.com','$2a$10$X1o7/R4ZhmNvEXFBvli3SuhsUEDyUAKjRqLhP1N3MaOE6DTx/mpOi', 'wcode');
INSERT INTO users(email, password, username) VALUES('admin@wcode.com','$2a$10$X1o7/R4ZhmNvEXFBvli3SuhsUEDyUAKjRqLhP1N3MaOE6DTx/mpOi', 'admin');
INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 1);
INSERT INTO RESUME(ABOUT_ME, ADDRESS, FULLNAME, PHONE, ZIP, USER_ID) VALUES('ABOUT_ME','ADDRESS', 'FULLNAME', 'PHONE', 'ZIP',1);
