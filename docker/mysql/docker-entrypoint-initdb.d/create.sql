use springtest;
DROP TABLE IF EXISTS user_group;
create table user_group
(
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(30) not null UNIQUE,
  address varchar(30),
  city varchar(14),
  stateOrProvince varchar(15),
  country varchar(10),
  postalCode varchar(10),
  user_updated varchar(25),
  date_created timestamp,
  last_updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  update_application varchar(25) DEFAULT 'SQL' ,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

