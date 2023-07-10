CREATE DATABASE identity_reconciliation;
USE identity_reconciliation;

CREATE TABLE contact (
  id int PRIMARY KEY,
  created_at timestamp NOT NULL,
  deleted_at timestamp DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  link_precendence varchar(255) NOT NULL,
  linked_id int DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  updated_at timestamp NOT NULL
);

CREATE TABLE hibernate_sequence (
  next_val BIGINT
) ENGINE=InnoDB;

INSERT INTO hibernate_sequence (next_val) VALUES (1);

-- Create user
CREATE USER 'blaster'@'%' IDENTIFIED BY 'blaster@25';

-- Grant privileges to the user on the database
GRANT ALL PRIVILEGES ON *.* TO 'blaster'@'%';