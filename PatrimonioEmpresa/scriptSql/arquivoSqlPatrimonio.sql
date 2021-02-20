CREATE TABLE tb_usuario(
	id SERIAL PRIMARY KEY,
	nome VARCHAR (255) NOT NULL,
	email VARCHAR (255) UNIQUE NOT NULL,
	senha VARCHAR (255) NOT NULL
);

CREATE TABLE tb_marca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR (255) NOT NULL UNIQUE,
    tb_marca_id INTEGER NOT NULL
);

CREATE TABLE tb_patrimonio (
	id SERIAL,
	nome VARCHAR (255),
	descricao TEXT,
	marca_id INTEGER NOT NULL,
  	numero_tombo INTEGER UNIQUE,
  	FOREIGN KEY (marca_id) REFERENCES tb_marca(id) ON DELETE CASCADE
);

INSERT INTO tb_usuario(nome, email, senha) VALUES ('Joao', 'keniyav596@chordmi.com', 
	'$2a$10$9qihMO0a4bWoF1pHCVSK4Ojn1rx4BHWPKPdOJqRBp4jVg6Jtn3Vv2');