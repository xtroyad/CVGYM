drop database mysql_database_gimnasio;
create database mysql_database_gimnasio;
use mysql_database_gimnasio;

drop table IF EXISTS manager;
CREATE TABLE `manager` (
  `id`  bigint auto_increment NOT NULL,
  `last_name` varchar(255),
  `name` varchar(255),
  PRIMARY KEY (id)
);

drop table IF EXISTS gym;
CREATE TABLE `gym` (
  `id` bigint auto_increment NOT NULL,
  `address` varchar(255),
  `ccaa` varchar(255),
  `city` varchar(255),
  `number` varchar(255),
  `phone_number` varchar(255),
  `zip` varchar(255),
  `manager_id` bigint,
	PRIMARY KEY (id),
    FOREIGN KEY (manager_id) REFERENCES manager(id)
);

drop table IF EXISTS question;
CREATE TABLE `question` (
  `id` bigint auto_increment NOT NULL,
  `description` varchar(255),
  `mail` varchar(255),
  `subject` varchar(255),
  `type_question` varchar(255),
  PRIMARY KEY (`id`)
);

drop table IF EXISTS coach;
CREATE TABLE `coach` (
  `id` bigint auto_increment NOT NULL,
  `last_name` varchar(255),
  `name` varchar(255),
  `gym_id` bigint,
  PRIMARY KEY (id),
  FOREIGN KEY (gym_id) REFERENCES gym(id)
);

drop table IF EXISTS course;
CREATE TABLE `course` (
  `id` bigint auto_increment NOT NULL,
  `description` varchar(255),
  `intensity` int,
  `name` varchar(255),
  PRIMARY KEY (id)
);

drop table IF EXISTS gym_courses;
CREATE TABLE `gym_courses` (
  `gyms_id` bigint NOT NULL,
  `courses_id` bigint NOT NULL,
   FOREIGN KEY (courses_id) REFERENCES course(id),
   FOREIGN KEY (gyms_id) REFERENCES gym(id)
)