create database bankingapp;
use bankingapp;

CREATE TABLE person(
person_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT
dpi VARCHAR(32) NOT NULL,
first_name VARCHAR(40) NOT NULL,
second_name VARCHAR(40) NULL,
third_name VARCHAR(40) NULL,
last_name VARCHAR(40) NOT NULL,
second_last_name VARCHAR(40) NULL,
address VARCHAR(256) NOT NULL,
phone_number VARCHAR(40) NULL INT NULL,
home_phone_number VARCHAR(40) NULL,
email_address VARCHAR(128) NULL
);

CREATE TABLE currency(
currency_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
description VARCHAR(16) NOT NULL
);

CREATE TABLE transaction_category(
transaction_category_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
description VARCHAR(64) NOT NULL
);


CREATE TABLE bank(
bank_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(128) NOT NULL,
address VARCHAR(256) NOT NULL,
cash_available  DECIMAL(9,2) NOT NULL
);

CREATE TABLE currency_bank(
bank_id INT NOT NULL,
currency_id INT NOT NULL,

CONSTRAINT pk_bank_id__currency_id PRIMARY KEY(bank_id, currency_id),
CONSTRAINT fk_currency_bank__bank FOREIGN KEY(bank_id) REFERENCES bank(bank_id),
CONSTRAINT fk_currency_bank__currency FOREIGN KEY(currency_id) REFERENCES currency(currency_id)
);

CREATE TABLE bank_account(
bank_account_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
bank_id INT NOT NULL,
balance DECIMAL(6,2),
CONSTRAINT fk_bank_account__bank FOREIGN KEY(bank_id) REFERENCES bank(bank_id)
) AUTO_INCREMENT = 1111;

CREATE TABLE user_account(
user_account_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
user_name VARCHAR(64) NOT NULL,
password VARCHAR(128) NOT NULL,
created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
person_id INT NOT NULL,

CONSTRAINT fk_user_account__person FOREIGN KEY(person_id) REFERENCES person(person_id)
);

CREATE TABLE atm(
atm_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
bank_id INT NOT NULL,
address VARCHAR(256) NOT NULL,
cash_available DECIMAL(4,2) NOT NULL,

CONSTRAINT fk_atm__bank FOREIGN KEY(bank_id) REFERENCES bank(bank_id)
);

CREATE TABLE transaction_type(
transaction_type_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
description VARCHAR(128) NOT NULL,
transaction_category_id INT NOT NULL,

CONSTRAINT fk_transaction_type__transaction_category FOREIGN KEY(transaction_category_id) 
REFERENCES transaction_category(transaction_category_id)
);

CREATE TABLE banking_transaction(
banking_transaction_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
transaction_type_id INT NOT NULL,
amount DECIMAL(6,2) NULL,
atm_id INT NULL,
bank_id INT NULL,
bank_account_id INT NULL,
transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT fk_banking_transaction__atm FOREIGN KEY(atm_id) REFERENCES atm(atm_id),
CONSTRAINT fk_banking_transaction__bank FOREIGN KEY(bank_id) REFERENCES bank(bank_id),
CONSTRAINT fk_banking_transaction__bank_account FOREIGN KEY(bank_account_id) REFERENCES bank_account(bank_account_id)
);

create table role(
role_id int primary key not null auto_increment,
role_name varchar(32) not null
);

create table user_role(
user_role_id int primary key not null auto_increment,
user_account_id int not null,
role_id int not null,

CONSTRAINT fk_user_role_user_account FOREIGN KEY(user_account_id) REFERENCES user_account(user_account_id),
CONSTRAINT fk_user_role_role FOREIGN KEY(role_id) REFERENCES role(role_id)
);





