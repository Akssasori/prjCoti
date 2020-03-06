CREATE TABLE usuario(
id int(4) AUTO_INCREMENT,
login varchar(30) NOT NULL,
senha varchar(50),
PRIMARY KEY (id)
);

INSERT INTO usuario(id, login, senha) VALUES (null, “goku@gmail.com”, “123456”) ;
INSERT INTO usuario(id, login, senha) VALUES (null, “vegeta@gmail.com”, “654321”) ;
INSERT INTO usuario(id, login, senha) VALUES (null, “lu@gmail.com”, “123567”) ;
