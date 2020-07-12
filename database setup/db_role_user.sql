CREATE TABLE `departmentmanager`.`user_role` (
  `user_role_id` INT NOT NULL  AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`));
