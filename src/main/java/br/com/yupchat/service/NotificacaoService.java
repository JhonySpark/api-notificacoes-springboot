package br.com.yupchat.service;

import org.springframework.stereotype.Service;

import br.com.yupchat.dto.NotificacaoDto;
import br.com.yupchat.exception.CustomNotFoundException;
import br.com.yupchat.model.Notificacao;
import br.com.yupchat.model.TipoNotificacao;
import br.com.yupchat.model.User;
import br.com.yupchat.repository.NotificacaoRepository;
import br.com.yupchat.repository.TipoNotificacaoRepository;
import br.com.yupchat.repository.UserRepository;

import java.util.List;

@Service
public class NotificacaoService {
        private final NotificacaoRepository notificacaoRepository;
        private final UserRepository userRepository;
        private final TipoNotificacaoRepository tipoNotificacaoRepository;

        public NotificacaoService(NotificacaoRepository notificacaoRepository, UserRepository userRepository,
                        TipoNotificacaoRepository tipoNotificacaoRepository) {
                this.notificacaoRepository = notificacaoRepository;
                this.userRepository = userRepository;
                this.tipoNotificacaoRepository = tipoNotificacaoRepository;
        }

        public NotificacaoDto createNotification(NotificacaoDto notificacaoDto, String userEmail) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

                TipoNotificacao tipoNotificacao = tipoNotificacaoRepository
                                .findById(notificacaoDto.getidTipoNotificacao())
                                .orElseThrow(() -> new CustomNotFoundException(
                                                CustomNotFoundException.TIPO_NOTIFICACAO_NOT_FOUND));

                Notificacao notificacao = new Notificacao();
                notificacao.setTitulo(notificacaoDto.getTitulo());
                notificacao.setDescricao(notificacaoDto.getDescricao());
                notificacao.setCorpo(notificacaoDto.getCorpo());
                notificacao.setImagemLink(notificacaoDto.getimagem());
                notificacao.setUser(user);
                notificacao.setTipo(tipoNotificacao);

                return mapToDto(notificacaoRepository.save(notificacao));
        }

        public List<NotificacaoDto> getNotificationsByUser(String userEmail) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

                List<Notificacao> notificacoes = notificacaoRepository.findByUser(user);
                return notificacoes.stream().map(this::mapToDto).toList();
        }

        public List<NotificacaoDto> getNotificationsByType(Long typeId, String userEmail) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

                TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findById(typeId)
                                .orElseThrow(() -> new CustomNotFoundException(
                                                CustomNotFoundException.TIPO_NOTIFICACAO_NOT_FOUND));

                List<Notificacao> notificacoes = notificacaoRepository.findByUserAndTipo(user, tipoNotificacao);
                return notificacoes.stream().map(this::mapToDto).toList();
        }

        public NotificacaoDto updateNotification(Long notificationId, NotificacaoDto notificacaoDto, String userEmail) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

                Notificacao notificacao = notificacaoRepository.findByUserAndId(user, notificationId)
                                .orElseThrow(() -> new CustomNotFoundException(
                                                CustomNotFoundException.NOTIFICAO_NOT_FOUND));

                notificacao.setTitulo(notificacaoDto.getTitulo());
                notificacao.setDescricao(notificacaoDto.getDescricao());
                notificacao.setCorpo(notificacaoDto.getCorpo());
                notificacao.setImagemLink(notificacaoDto.getimagem());

                return mapToDto(notificacaoRepository.save(notificacao));
        }

        public void deleteNotification(Long notificationId, String userEmail) {
                User user = userRepository.findByEmail(userEmail)
                                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

                Notificacao notificacao = notificacaoRepository.findByUserAndId(user, notificationId)
                                .orElseThrow(() -> new CustomNotFoundException(
                                                CustomNotFoundException.NOTIFICAO_NOT_FOUND));

                notificacaoRepository.delete(notificacao);
        }

        private NotificacaoDto mapToDto(Notificacao notificacao) {
                NotificacaoDto notificacaoDto = new NotificacaoDto();
                notificacaoDto.setId(notificacao.getId());
                notificacaoDto.setTitulo(notificacao.getTitulo());
                notificacaoDto.setDescricao(notificacao.getDescricao());
                notificacaoDto.setCorpo(notificacao.getCorpo());
                notificacaoDto.setimagem(notificacao.getImagemLink());
                notificacaoDto.setidTipoNotificacao(notificacao.getTipo().getId());

                return notificacaoDto;
        }
}
