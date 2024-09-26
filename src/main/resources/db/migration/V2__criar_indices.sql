CREATE INDEX idx_user_email ON usuarios (email);

CREATE INDEX idx_notificacao_usuario_id ON notificacoes (id_usuario);

CREATE INDEX idx_notificacao_tipo_id ON notificacoes (id_tipo_notificacao);