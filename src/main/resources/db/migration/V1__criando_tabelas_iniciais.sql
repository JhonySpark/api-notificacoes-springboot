CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE tipos_notificacao (
    id BIGSERIAL PRIMARY KEY,
    nome_tipo VARCHAR(50) NOT NULL,
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);

CREATE TABLE notificacoes (
    id BIGSERIAL PRIMARY KEY,
    titulo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    corpo TEXT NOT NULL,
    imagem_destaque TEXT NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_tipo_notificacao BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios (id),
    FOREIGN KEY (id_tipo_notificacao) REFERENCES tipos_notificacao (id)
);