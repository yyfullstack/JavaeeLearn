CREATE DATABASE smart4j
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE smart4j;

CREATE TABLE customer (
  id        INT PRIMARY KEY,
  name      VARCHAR(40),
  contact   VARCHAR(40),
  telephone VARCHAR(40),
  email     VARCHAR(100),
  remark    VARCHAR(255)
);

INSERT INTO customer VALUES (1, 'customer1', 'Jack', '13615623211', 'jack@163.com', NULL);
INSERT INTO customer VALUES (2, 'customer2', 'Tom', '13615623211', 'tom@163.com', NULL);