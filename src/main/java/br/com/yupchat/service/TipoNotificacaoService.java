package br.com.yupchat.service;

import org.springframework.stereotype.Service;

import br.com.yupchat.dto.TipoNotificacaoDto;
import br.com.yupchat.exception.CustomNotFoundException;
import br.com.yupchat.exception.CustomNotPermitedException;
import br.com.yupchat.model.Notificacao;
import br.com.yupchat.model.TipoNotificacao;
import br.com.yupchat.model.User;
import br.com.yupchat.repository.NotificacaoRepository;
import br.com.yupchat.repository.TipoNotificacaoRepository;
import br.com.yupchat.repository.UserRepository;

import java.util.List;

@Service
public class TipoNotificacaoService {
    private final TipoNotificacaoRepository tipoNotificacaoRepository;
    private final UserRepository userRepository;
    private final NotificacaoRepository notificacaoRepository;

    public TipoNotificacaoService(TipoNotificacaoRepository tipoNotificacaoRepository, UserRepository userRepository,
            NotificacaoRepository notificacaoRepository) {
        this.tipoNotificacaoRepository = tipoNotificacaoRepository;
        this.userRepository = userRepository;
        this.notificacaoRepository = notificacaoRepository;
    }

    public TipoNotificacaoDto createTipoNotificacao(TipoNotificacaoDto tipoNotificacaoDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

        TipoNotificacao tipoNotificacao = new TipoNotificacao();
        tipoNotificacao.setNome(tipoNotificacaoDto.getNome());
        tipoNotificacao.setUser(user);

        return mapToDto(tipoNotificacaoRepository.save(tipoNotificacao));
    }

    public List<TipoNotificacaoDto> getTiposByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

        List<TipoNotificacao> tipos = tipoNotificacaoRepository.findByUser(user);
        return tipos.stream().map(this::mapToDto).toList();
    }

    public TipoNotificacaoDto updateTipoNotificacao(Long tipoId, TipoNotificacaoDto tipoNotificacaoDto,
            String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findByUserAndId(user, tipoId)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.TIPO_NOTIFICACAO_NOT_FOUND));

        tipoNotificacao.setNome(tipoNotificacaoDto.getNome());
        return mapToDto(tipoNotificacaoRepository.save(tipoNotificacao));
    }

    public void deleteTipoNotificacao(Long tipoId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND));

        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findByUserAndId(user, tipoId)
                .orElseThrow(() -> new CustomNotFoundException(CustomNotFoundException.TIPO_NOTIFICACAO_NOT_FOUND));

        List<Notificacao> notificacoes = notificacaoRepository.findByUserAndTipo(user, tipoNotificacao);
        if (!notificacoes.isEmpty()) {
            throw new CustomNotPermitedException(
                    CustomNotPermitedException.TIPO_NOTIFICACAO_REFERENCIADO);
        }

        tipoNotificacaoRepository.delete(tipoNotificacao);
    }

    private TipoNotificacaoDto mapToDto(TipoNotificacao tipoNotificacao) {
        TipoNotificacaoDto tipoNotificacaoDto = new TipoNotificacaoDto();
        tipoNotificacaoDto.setNome(tipoNotificacao.getNome());
        tipoNotificacaoDto.setId(tipoNotificacao.getId());
        tipoNotificacaoDto.setIdUsuario(tipoNotificacao.getUser().getId());

        return tipoNotificacaoDto;
    }
}
