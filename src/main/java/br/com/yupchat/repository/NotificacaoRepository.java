package br.com.yupchat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yupchat.model.Notificacao;
import br.com.yupchat.model.TipoNotificacao;
import br.com.yupchat.model.User;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUser(User user);
    List<Notificacao> findByUserAndTipo(User user, TipoNotificacao tipo);
    Optional<Notificacao> findByUserAndId(User user, Long id);
}
