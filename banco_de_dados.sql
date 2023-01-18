CREATE DATABASE myanimes;
USE myanimes;
CREATE TABLE animes(
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(64) NOT NULL,
    ano INT NOT NULL,
    descricao VARCHAR(120),
    avaliacao VARCHAR(8),
    status INT NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO animes(titulo, ano, descricao, avaliacao, status) VALUES("Pokémon", 2000, "Monstros alienigenas que são usados para lutar até a morte por seus.", "Mediano", 1);
