CREATE DATABASE IF NOT EXISTS education;
USE education;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS roles;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS roles
(
    id int,
    name varchar(30) unique NOT NULL,
    PRIMARY KEY (id)
);

-- INSERT VALUES TO ROLES
INSERT INTO roles VALUES (0, 'teacher');
INSERT INTO roles VALUES (1, 'student');
INSERT INTO roles VALUES (2, 'admin');

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS users;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS users
(
	id INT auto_increment,
    login varchar(35) default 'user' NOT NULL unique,
    password varchar(45) NOT NULL,
    role_id int not null,
    first_name varchar(35) NOT NULL,
    last_name varchar(35) NOT NULL,
    group_id int not null,
    FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (group_id)
        REFERENCES groups(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

-- INSERT VALUES TO USERS
INSERT INTO users(login, password, role_id, first_name, last_name, group_id) VALUES ('admin', 'admin', 0, 'Admin', 'Lastivka', 0);
INSERT INTO users(login, password, role_id, first_name, last_name, group_id) VALUES ('student', 'student', 1, 'Student', 'Hardworker', 0);

SET foreign_key_checks = 0;
drop table if exists groups;
SET foreign_key_checks = 1;

create table if not exists groups
(
    id int auto_increment,
    name varchar(30) NOT NULL unique,
    PRIMARY KEY (id)
);

SET foreign_key_checks = 0;
drop table if exists courses;
SET foreign_key_checks = 1;

create table if not exists courses
(
    id INT auto_increment,
    name varchar(30) NOT NULL unique,
    PRIMARY KEY (id)
);

SET foreign_key_checks = 0;
drop table if exists courses_have_users;
SET foreign_key_checks = 1;

create table if not exists courses_have_users
(
    user_id int not null,
    foreign key (user_id)
        references users(id)
        on delete no action on update no action ,
    course_id int not null,
    foreign key (course_id)
        references courses(id)
        on delete no action on update no action
);
-- INSERT VALUES TO COURSES_HAVE_USERS

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS posts;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS posts
(
    id int auto_increment,
    title varchar(30) NOT NULL,
    subtitle varchar(50) NOT NULL,
    deadline varchar(20),
    file_id int null,
    foreign key (file_id)
        references files(id)
        on delete cascade on update no action,
    PRIMARY KEY (id)
);

-- INSERT VALUES TO POSTS

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS files;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS files
(
    id int auto_increment,
    content_type varchar(30) NOT NULL,
    content mediumblob NOT NULL,
    PRIMARY KEY(id)
);

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS homeworks;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS homeworks
(
    id int auto_increment,
    content_type varchar(30) NOT NULL,
    content mediumblob NOT NULL,
    PRIMARY KEY(id)
);

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS users_have_posts;
SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS users_have_posts
(
    user_id int not null,
    foreign key (user_id)
        references users(id)
        on delete cascade on update no action,
    post_id int not null,
    foreign key (post_id)
        references posts(id)
        on delete cascade on update no action,
    homework_id int not null,
    foreign key (homework_id)
        references homeworks(id)
        on delete cascade on update no action,
    mark int null
);

SET foreign_key_checks = 0;
drop table if exists courses_have_posts;
SET foreign_key_checks = 1;

create table if not exists courses_have_posts
(
    course_id int not null,
    foreign key (course_id)
        references courses(id)
        on delete cascade on update cascade,
    post_id int not null,
    foreign key (post_id)
        references posts(id)
        on delete cascade on update cascade
);

-- INSERT VALUES TO COURSES_HAVE_POSTS