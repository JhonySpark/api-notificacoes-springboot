package br.com.yupchat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yupchat.model.TipoNotificacao;
import br.com.yupchat.model.User;

public interface TipoNotificacaoRepository extends JpaRepository<TipoNotificacao, Long> {
    List<TipoNotificacao> findByUser(User user);
    Optional<TipoNotificacao> findByUserAndId(User user, Long id);
}
