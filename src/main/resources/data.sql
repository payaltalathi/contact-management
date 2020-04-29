DROP TABLE IF EXISTS contact;

CREATE TABLE contact (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  phone_number VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL
);


INSERT INTO contact (first_name, last_name, email, phone_number, status) VALUES
  ('Payal', 'Talathi', 'payaltalathi@gmail.com', '+918080274116', 'ACTIVE'),
  ('Yash', 'Talathi', 'yash.talathi1998@gmail.com', '08390260323', 'INACTIVE');
