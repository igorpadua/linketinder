-- Tabelas

CREATE TABLE competencias (
  id SERIAL PRIMARY KEY,
  competencia varchar(30) NOT NULL
);

CREATE TABLE candidatos (
  id SERIAL PRIMARY KEY,
  nome char(15) NOT NULL,
  sobrenome varchar NOT NULL,
  data_nascimento date NOT NULL,
  email varchar(50) UNIQUE NOT NULL,
  cpf char(14) UNIQUE NOT NULL,
  pais varchar(50) NOT NULL,
  cep char(9) NOT NULL,
  descricao varchar(100) NOT NULL,
  senha char(16) NOT NULL
);

CREATE TABLE competencias_candidato (
  id SERIAL PRIMARY KEY,
  competencia_id int REFERENCES competencias(id) NOT NULL,
  candidatos_id int REFERENCES candidatos(id) NOT NULL
);

CREATE TABLE empresas (
  id SERIAL PRIMARY KEY,
  nome char(15) NOT NULL,
  cnpj char(18) UNIQUE NOT NULL,
  email char(50) UNIQUE NOT NULL,
  descricao varchar(100) NOT NULL,
  pais varchar(50) NOT NULL,
  cep varchar(9) NOT NULL,
  senha varchar(16) NOT NULL
);

CREATE TABLE vagas (
  id SERIAL PRIMARY KEY,
  nome varchar(50) NOT NULL,
  descricao varchar(100) NOT NULL,
  local_vaga varchar(50) NOT NULL,
  empresa_id int REFERENCES empresas(id) NOT NULL
);

CREATE TABLE competencia_vagas (
  id SERIAL PRIMARY KEY,
  competencia_id int REFERENCES competencias(id) NOT NULL,
  vagas_id int REFERENCES vagas(id) NOT NULL
);

CREATE TABLE curtidas (
  id SERIAL PRIMARY KEY,
  curtiu int NOT NULL
);

CREATE TABLE curtida_empresa (
  id SERIAL PRIMARY KEY,
  empresa_id int REFERENCES empresas(id) NOT NULL,
  cutida_id int REFERENCES curtidas(id) NOT NULL
);

CREATE TABLE curtida_candidato (
  id SERIAL PRIMARY KEY,
  candidato_id int REFERENCES candidatos(id) NOT NULL,
  cutida_id int REFERENCES curtidas(id) NOT NULL
);

-- Insert

-- Competencias
INSERT INTO competencias (competencia) VALUES ('cplusplus');
INSERT INTO competencias (competencia) VALUES ('javascript');
INSERT INTO competencias (competencia) VALUES ('angular');
INSERT INTO competencias (competencia) VALUES ('nodejs');
INSERT INTO competencias (competencia) VALUES ('java');
INSERT INTO competencias (competencia) VALUES ('html');
INSERT INTO competencias (competencia) VALUES ('c');
INSERT INTO competencias (competencia) VALUES ('python');

-- Candidatos
INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
	VALUES ('Joao', 'Vitor', '1998-03-23', 'joao@gmail.com' ,'100.000.000-00', 'Brasil', '00000-000', 'Desenvolvedor back-end', '12345');
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (1,1);
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (4,1);

INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
	VALUES ('Natalia', 'Alves', '2000-01-30', 'natalia@gmail.com' ,'200.000.000-00', 'Brasil', '00000-000', 'Desenvolvedora back-end', '12345');
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (5,2);
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (8,2);

INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
	VALUES ('Maria', 'Silva', '1995-05-11', 'maria@gmail.com' ,'300.000.000-00', 'Brasil', '00000-000', 'Desenvolvedora front-end', '12345');
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (3,3);
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (6,3);

INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
	VALUES ('Vitor', 'Pereira', '2001-11-23', 'vitor@gmail.com' ,'400.000.000-00', 'Brasil', '00000-000', 'Desenvolvedor back-end', '12345');
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (1,4);
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (4,4);

INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha)
	VALUES ('Paulo', 'Ribeiro', '1993-12-23', 'paulo@gmail.com' ,'500.000.000-00', 'Brasil', '00000-000', 'Desenvolvedor front-end', '12345');
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (3,5);
INSERT INTO competencias_candidato (competencia_id, candidatos_id) VALUES (6,5);

-- Empresas
INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
	VALUES('main.groovy.entity.Empresa 1', '00.000.000/0000-00', 'empresa1@gmail.com', 'main.groovy.entity.Empresa de jogos', 'Brasil', '00000-000', '12345');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
	VALUES('main.groovy.entity.Empresa 2', '10.000.000/0000-00', 'empresa2@gmail.com', 'Farmacia', 'Brasil', '00000-000', '12345');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
	VALUES('main.groovy.entity.Empresa 3', '20.000.000/0000-00', 'empresa3@gmail.com', 'main.groovy.entity.Empresa de vendas', 'Brasil', '00000-000', '12345');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
	VALUES('main.groovy.entity.Empresa 4', '30.000.000/0000-00', 'empresa4@gmail.com', 'main.groovy.entity.Empresa de jogos', 'Brasil', '00000-000', '12345');

INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha)
	VALUES('main.groovy.entity.Empresa 5', '40.000.000/0000-00', 'empresa5@gmail.com', 'main.groovy.entity.Empresa de software', 'Brasil', '00000-000', '12345');

-- Vagas
INSERT INTO vagas (nome, descricao, local_vaga, empresa_id)
	VALUES('Vaga junior', 'Para desenvolvedores back-end', 'São Paulo', 1);
INSERT INTO competencia_vagas (competencia_id, vagas_id) VALUES (1,1);
INSERT INTO competencia_vagas (competencia_id, vagas_id) VALUES (4,1);

INSERT INTO vagas (nome, descricao, local_vaga, empresa_id)
	VALUES('Vaga senior', 'Para desenvolvedores front-end', 'Rio de Janeiro', 2);
INSERT INTO competencia_vagas (competencia_id, vagas_id) VALUES (3,2);
INSERT INTO competencia_vagas (competencia_id, vagas_id) VALUES (6,2);
