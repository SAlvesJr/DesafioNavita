create table tb_usuario(
	id SERIAL PRIMARY KEY,
	email VARCHAR (255) UNIQUE NOT NULL,
	senha VARCHAR (255) NOT NULL
)

CREATE TABLE tb_marca (
    id SERIAL PRIMARY KEY,
    nome VARCHAR (255) NOT NULL UNIQUE,
);

CREATE TABLE tb_patrimonio (
	id SERIAL,
	nome VARCHAR (255),
	marca_id INTEGER NOT NULL,
	descricao TEXT,  
  	numero_tombo INTEGER UNIQUE,
  	FOREIGN KEY (marca_id) REFERENCES tb_marca(id) ON DELETE CASCADE
);

INSERT INTO tb_usuario(email, senha) VALUES ('keniyav596@chordmi.com', '12345');