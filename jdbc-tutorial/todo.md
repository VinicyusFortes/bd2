-- exemplo de como criar uma tabela com id dinamico:
- é preciso usar o atributo SERIAL para que o id seja incrementado automaticamente

CREATE TABLE musica (
id SERIAL PRIMARY KEY,  -- ou BIGSERIAL dependendo da necessidade
titulo_musica VARCHAR(50) NOT NULL,
data_criacao DATE NOT NULL,
genero VARCHAR(30)
);