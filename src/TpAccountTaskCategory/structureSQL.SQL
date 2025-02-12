use tp_java;
--
-- creation des tables
--
CREATE TABLE account(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(100) NOT NULL
)Engine=InnoDB;

CREATE TABLE task(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
title VARCHAR(50) NOT NULL,
`description` VARCHAR(255) NOT NULL,
createdAt DATETIME NOT NULL,
status TINYINT(1),
account_id INT
)Engine=InnoDB;

CREATE TABLE category(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(50) NOT NULL UNIQUE
)Engine=InnoDB;

CREATE TABLE task_category(
task_id INT,
category_id INT,
PRIMARY KEY (task_id, category_id)
)Engine=InnoDB;
--
-- contraites Foreign Key
--
ALTER TABLE task
ADD CONSTRAINT fk_task_account
FOREIGN KEY (account_id) REFERENCES account(id);

ALTER TABLE task_category
ADD CONSTRAINT fk_task_category_task
FOREIGN KEY (task_id) REFERENCES task(id),
ADD CONSTRAINT fk_task_category_category
FOREIGN KEY (category_id) REFERENCES category(id);