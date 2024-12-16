CREATE DATABASE IF NOT EXISTS EazyOrder;
USE EazyOrder;

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_pessoa VARCHAR(255) NOT NULL,  
    valor_total DECIMAL(10, 2) NOT NULL,  
    produtos VARCHAR(255),  
    forma_pagamento CHAR(1) NOT NULL
);

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao TEXT
);

INSERT INTO produtos (nome, preco, descricao) VALUES
('X-BURGUER', 18.00, 'PAO CARNE QUEIJO'),
('X-BACON', 23.00, 'PAO CARNE QUEIJO BACON'),
('COCA-COLA', 8.00, 'LATA 350ML - NORMAL');

SELECT * FROM pedidos;
SELECT * FROM produtos;

-- garantir privilegios
SELECT User, Host FROM mysql.user;
GRANT ALL PRIVILEGES ON eazyorder.* TO 'empresa'@'%';
FLUSH PRIVILEGES;


