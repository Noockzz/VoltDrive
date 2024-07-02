CREATE DATABASE VoltDrive
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

USE VoltDrive;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150),
    email VARCHAR(100),
    senha VARCHAR(30)
);

ALTER TABLE usuarios
ADD COLUMN administrador BOOLEAN DEFAULT FALSE AFTER id;

ALTER TABLE usuarios
ADD CONSTRAINT chk_administrador CHECK (administrador IN (TRUE, FALSE));

INSERT INTO usuarios
    (administrador, nome, email, senha)
VALUES
    (TRUE, 'Luiz Gustavo Administrator', 'luizadm@gmail.com', 'lzgadm123'),
    (TRUE, 'Luana Karolina Administrator', 'luanaadm@gmail.com', 'lakaadm123'),
    (TRUE, 'Arthur Guedes Administrator', 'arthuradm@gmail.com', 'argadm123'),
    (TRUE, 'Adrian Aguiar Administrator', 'adrianadm@gmail.com', 'anaadm123');

CREATE TABLE carros (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50),
    modelo VARCHAR(100),
    descricao TEXT,
    imagem VARCHAR(255)
);

INSERT INTO carros 
    (marca, modelo, descricao, imagem) 
VALUES
    ('BMW', 'iX', 'O BMW iX é um SUV elétrico de luxo com design futurista, alta performance e grande autonomia. Oferece interior espaçoso, materiais sustentáveis e tecnologia avançada, proporcionando uma experiência de condução premium e sustentável. R$ 435.000', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/BMW-ix.jpg'),
    ('BMW', 'iX1', 'O BMW iX1 é um SUV elétrico compacto, com design moderno, desempenho ágil, interior luxuoso e tecnologias avançadas, oferecendo uma condução sustentável e sofisticada. R$ 425.000', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/BMW-ix1.jpg'),
    ('BMW', 'Série 3', 'O BMW Série 3 é um sedã compacto de luxo, conhecido por seu design esportivo e sofisticado, desempenho dinâmico e tecnologia avançada.  R$ 222.000', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/BMW-Serie3.png'),
    ('TESLA', 'Model S', 'O Tesla Model S é um sedan elétrico de alto desempenho, conhecido por seu design elegante e aerodinâmico. Oferece um interior luxuoso, espaçoso e repleto de tecnologia avançada, incluindo o Autopilot para assistência ao condutor. Com motores elétricos potentes, proporciona uma aceleração rápida e uma ampla autonomia, sendo uma referência em termos de eficiência energética e inovação no mercado de veículos elétricos de luxo. R$ 1.276.840', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/TESLA-ModelS.png'),
    ('TESLA', 'Model X', 'O Tesla Model X é um SUV elétrico que combina desempenho excepcional, design inovador e conforto premium. Destacando-se pelas portas Falcon Wing e um espaçoso interior para até sete adultos, oferece tecnologia avançada de segurança e capacidade de autopilotagem. Com uma impressionante aceleração de 0 a 60 km/h em apenas 2,6 segundos, sua autonomia estendida e carregamento rápido o posicionam como líder entre os SUVs elétricos de alto padrão. R$441 mil', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/TESLA-ModelX.jpg'),
    ('TESLA', 'Roadster', 'O Tesla Roadster é um carro esportivo elétrico de alto desempenho conhecido por sua aceleração excepcional (0 a 100 km/h em menos de 2 segundos) e design aerodinâmico impressionante. Equipado com tecnologia avançada e uma bateria de longa duração, combina luxo, conforto e sustentabilidade, representando um marco significativo na indústria automotiva elétrica. R$ 990.000 á R$ 1,23 milhão', 'C:/Users/Luana/Documents/Luana/Java/voltDrive/src/Imagens/TESLA-Roadster.png');

SELECT DISTINCT modelo, marca
FROM carros
ORDER BY marca;

SELECT * FROM usuarios;
SELECT * FROM carros;
