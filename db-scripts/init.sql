-- Usar o banco de dados correto
USE meu_banco;

-- Criar tabela de usuários
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criar tabela de produtos, com chave estrangeira para o usuário e estado do produto
CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    state ENUM('disponível', 'reservado', 'vendido') DEFAULT 'disponível',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

-- Inserir usuários iniciais
INSERT INTO user (name, password) VALUES
('João', '123456'),
('Maria', 'abcdef'),
('Carlos', 'senha123'),
('Ana', 'password123'),
('Paulo', '123abc'),
('Fernanda', 'abc123'),
('Lucas', 'senha321'),
('Juliana', 'mypassword'),
('Gustavo', 'qwerty123'),
('Carla', 'abcde123');

-- Inserir produtos para teste
INSERT INTO product (user_id, name, description, price, state) VALUES
(1, 'Notebook', 'Notebook Gamer com 16GB RAM', 4500.00, 'disponível'),
(1, 'Mouse', 'Mouse sem fio', 150.00, 'reservado'),
(1, 'Teclado Mecânico', 'Teclado RGB Switch Red', 350.00, 'vendido'),
(2, 'Smartphone', 'Smartphone com 64GB de memória', 1200.00, 'disponível'),
(2, 'Fones de Ouvido', 'Fones de ouvido Bluetooth', 200.00, 'disponível'),
(3, 'Câmera Digital', 'Câmera profissional DSLR', 2500.00, 'vendido'),
(4, 'Monitor', 'Monitor LED 24 polegadas', 900.00, 'disponível'),
(5, 'Tablet', 'Tablet 10 polegadas com 4GB de RAM', 800.00, 'reservado'),
(6, 'Headset', 'Headset com microfone para PC', 350.00, 'disponível'),
(7, 'Smartwatch', 'Smartwatch com monitor de batimentos cardíacos', 500.00, 'disponível');

