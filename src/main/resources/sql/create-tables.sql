create TABLE if not EXISTS  people (
  person_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(20),
  last_name  varchar(20)
);

create TABLE if not EXISTS  send_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  item varchar(50)
);
