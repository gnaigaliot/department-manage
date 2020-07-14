drop database departmentmanager;
create database departmentmanager CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;
use departmentmanager;
-- Phân quyền -------------------------------------------------------------------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(50),
  `role_name` varchar(200),
  PRIMARY KEY (`role_id`) USING BTREE
);

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'ROLE_ADMIN', 'Quản trị viên');
INSERT INTO `roles` VALUES (2, 'ROLE_SV', 'Quản lý phòng');
INSERT INTO `roles` VALUES (3, 'ROLE_GVCN', 'Quản lý hóa đơn');
INSERT INTO `roles` VALUES (4, 'ROLE_GVPT', 'Quản lý thông báo');


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100),
  `password` varchar(100),
  `full_name` varchar(200),
  `date_of_birth` date NULL DEFAULT NULL,
  `gender` tinyint(2) NULL DEFAULT NULL,
  `email` varchar(50),
  `mobile_number` varchar(50),
  `position_id` bigint(20) NULL DEFAULT NULL,
  `user_code` varchar(100),
  `created_date` datetime(0) NULL DEFAULT NULL,
  `created_by` varchar(200),
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `position_id`(`position_id`) USING BTREE
);

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', 'Phan Trịnh Đẹp Trai', '1996-10-23', 1, 'kito2310@gmail.com', '0123456789', 2, '66DCHT21668', NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;

create table if not exists complaint(
	id int not null auto_increment,
	code varchar(10),
	email varchar(20),
	
	primary key (id)
	
);

create table if not exists notification(
	id int not null auto_increment,
	code varchar(10),
	content text,
	title varchar(100),
	
	primary key (id)
);

create table if not exists employee(
	id int auto_increment,
	code varchar(10),
	name varchar(50),
	date_of_bird date,
	gender tinyint,
	username varchar(50),
	password varchar(50),
	email varchar(50),
	phonenumber varchar(20),
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	primary key (id)
);

create table if not exists positions(
	id int auto_increment,
	code varchar(10),
	
	name varchar(50),
	salary decimal,
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	primary key (id)
);

create table if not exists department(
	id int auto_increment,
	code varchar(10),
	name varchar(50),
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	primary key(id)
);

create table if not exists work_process(
	id int auto_increment,
	code varchar(10),
	start_date date,
	end_date date,
	
	id_department int not null,
	id_positions int not null,
	id_employee int not null,

	
	primary key (id),
	foreign key(id_department) references department(id),
	foreign key(id_positions) references positions(id),
	foreign key(id_employee) references employee(id)
);


create table if not exists apartment (
	id int auto_increment,
	code varchar(10),
	price decimal,
	area decimal,
	description text,
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	primary key (id)
);


create table if not exists person(
	id int auto_increment,
	code varchar(10),
	name varchar(50),
	gender tinyint,
	address varchar(200),
	identity_number varchar(20),
	date_of_bird date,
	
	primary key (id)
);

create table if not exists apartment_detail (
	id int auto_increment,
	name varchar(50),
	start_date date,
	end_date date,
	status tinyint,
	
	id_person int not null,
	id_apartment int not null,
	
	primary key (id),
	foreign key(id_person) references person(id),
	foreign key(id_apartment) references apartment(id)
);

create table if not exists contract (
	id int auto_increment,
	code varchar(10),
	from_date date,
	to_date date,
	price decimal,
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	id_person int not null,
	id_apartment int not null,
	
	primary key (id),
	foreign key(id_person) references person(id),
	foreign key(id_apartment) references apartment(id)
);

create table if not exists bill_electrict (
	id int auto_increment,
	code varchar(10),
	old_number decimal,
	new_number decimal,
	amount decimal,
	from_date date,
	to_date date,
	status tinyint,
	month int,
	year int,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	id_department int not null,
	
	primary key (id),
	foreign key(id_department) references department(id)
	
);

create table if not exists bill_water (
	id int auto_increment,
	code varchar(10),
	old_number decimal,
	new_number decimal,
	from_date date,
	to_date date,
	status tinyint,
	month int,
	year int,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	id_department int not null,
	
	primary key (id),
	foreign key(id_department) references department(id)
);

create table if not exists service_type (
	id int auto_increment,
	code varchar(10),
	name varchar(50),
	price decimal,
	unit varchar(20),
	status tinyint,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	primary key (id)
);

create table if not exists bill_service (
	id int auto_increment,
	code varchar(10),
	from_date date,
	to_date date,
	status tinyint,
	month int,
	year int,
	created_date date,
	edited_date date,
	created_by varchar(50),
	edited_by varchar(50),
	
	id_department int not null,
	
	primary key (id),
	foreign key(id_department) references department(id)
);

create table if not exists bill_service_detail (
	id int auto_increment,
	
	amount decimal,
	price decimal,
	
	id_bill_service int not null,
	id_service_type int not null,
	
	
	primary key (id),
	foreign key(id_bill_service) references bill_service(id),
	foreign key(id_service_type) references service_type(id)
);
	


alter table employee drop column username;
alter table employee drop column password;
alter table employee add column is_working smallint;

alter table work_process drop column code;