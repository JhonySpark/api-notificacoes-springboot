package br.com.yupchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.yupchat.dto.TipoNotificacaoDto;
import br.com.yupchat.service.TipoNotificacaoService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/type")
public class TipoNotificacaoController {
    private final TipoNotificacaoService tipoNotificacaoService;

    public TipoNotificacaoController(TipoNotificacaoService tipoNotificacaoService) {
        this.tipoNotificacaoService = tipoNotificacaoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTipoNotificacao(
            @RequestBody TipoNotificacaoDto tipoNotificacaoDto, Principal principal) {
        return ResponseEntity.ok(tipoNotificacaoService.createTipoNotificacao(tipoNotificacaoDto, principal.getName()));
    }

    @PostMapping("/update/{tipo_id}")
    public ResponseEntity<?> updateTipoNotificacao(
            @PathVariable("tipo_id") Long tipoId, @RequestBody TipoNotificacaoDto tipoNotificacaoDto,
            Principal principal) {
        return ResponseEntity
                .ok(tipoNotificacaoService.updateTipoNotificacao(tipoId, tipoNotificacaoDto, principal.getName()));
    }

    @PostMapping("/delete/{tipo_id}")
    public ResponseEntity<?> deleteTipoNotificacao(
            @PathVariable("tipo_id") Long tipoId, Principal principal) {
        tipoNotificacaoService.deleteTipoNotificacao(tipoId, principal.getName());
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Tipo de notificação deletado com sucesso");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<TipoNotificacaoDto>> getTiposNotificacao(Principal principal) {
        List<TipoNotificacaoDto> tiposNotificacao = tipoNotificacaoService.getTiposByUser(principal.getName());
        return ResponseEntity.ok(tiposNotificacao);
    }
}
