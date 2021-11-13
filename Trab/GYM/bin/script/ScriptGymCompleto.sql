CREATE DATABASE IF NOT EXISTS db_academia /*!40100 DEFAULT CHARACTER SET utf8 */;

DROP TABLE IF EXISTS db_academia.tb_cliente;
CREATE TABLE IF NOT EXISTS db_academia.tb_cliente (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    dataNascimento CHAR(10) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_horas;
CREATE TABLE IF NOT EXISTS db_academia.tb_horas(
	id INT NOT NULL PRIMARY KEY,
    descricao CHAR(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_plano;
CREATE TABLE IF NOT EXISTS db_academia.tb_plano (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    mensalidade INT NOT NULL,
    horas_diarias INT NOT NULL,
    cliente INT NOT NULL,
    
    CONSTRAINT fk_horas_plano
		FOREIGN KEY (horas_diarias) 
		REFERENCES tb_horas(id),
    
    CONSTRAINT fk_cliente_plano
		FOREIGN KEY (cliente) 
		REFERENCES tb_cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_ficha_medica;
CREATE TABLE IF NOT EXISTS db_academia.tb_ficha_medica (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    peso_atua FLOAT NOT NULL,
    altura FLOAT NOT NULL,
    imc DOUBLE NOT NULL,
    cliente INT NOT NULL,
    
    CONSTRAINT fk_cliente_ficMed
		FOREIGN KEY (cliente) 
		REFERENCES tb_cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_dias;
CREATE TABLE IF NOT EXISTS db_academia.tb_dias(
	id INT NOT NULL PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_registro_treinos;
CREATE TABLE IF NOT EXISTS db_academia.tb_registro_treinos (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    num_semana INT NOT NULL,
    ano INT NOT NULL,
    dia_semana INT NOT NULL,
    cliente INT NOT NULL,
    
    CONSTRAINT fk_dias_registro
		FOREIGN KEY (dia_semana) 
		REFERENCES tb_dias(id),
    
    CONSTRAINT fk_cliente_registro
		FOREIGN KEY (cliente) 
		REFERENCES tb_cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS db_academia.tb_pagamento;
CREATE TABLE IF NOT EXISTS db_academia.tb_pagamento (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    valor FLOAT NOT NULL,
    data_realizada CHAR(10) NOT NULL,
    semana_vencimento INT NOT NULL,
    cliente INT NOT NULL,

    CONSTRAINT fk_cliente_pagamento
		FOREIGN KEY (cliente)
		REFERENCES tb_cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO db_academia.tb_horas (id, descricao) VALUES 
(1,"00:30"),
(2,"01:00"),
(3,"01:30"),
(4,"02:00");

INSERT INTO db_academia.tb_dias(id, descricao) VALUES 
(1,"Segunda-feira"),
(2,"Terça-feira"),
(3,"Quarta-feira"),
(4,"Quinta-feira"),
(5,"Sexta-feira"),
(6,"Sabado"),
(7,"Domingo");

