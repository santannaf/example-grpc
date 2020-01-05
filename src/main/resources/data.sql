DROP TABLE IF EXISTS blog;

CREATE TABLE blog (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  message VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL
);

INSERT INTO blog (title, message, author) VALUES ('Hello World', 'This is first Example with gRPC!', 'Unknow');