package br.com.yupchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.yupchat.dto.NotificacaoDto;
import br.com.yupchat.service.NotificacaoService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNotification(@RequestBody NotificacaoDto notificacaoDto, Principal principal) {
        return ResponseEntity.ok(
                notificacaoService.createNotification(notificacaoDto, principal.getName()));
    }

    @PostMapping("/update/{news_id}")
    public ResponseEntity<?> updateNotification(@PathVariable("news_id") Long notificationId,
            @RequestBody NotificacaoDto notificacaoDto, Principal principal) {

        return ResponseEntity.ok(
                notificacaoService.updateNotification(notificationId, notificacaoDto, principal.getName()));
    }

    @PostMapping("/delete/{news_id}")
    public ResponseEntity<?> deleteNotification(@PathVariable("news_id") Long notificationId, Principal principal) {
        notificacaoService.deleteNotification(notificationId, principal.getName());
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Notificação deletada com sucesso");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<NotificacaoDto>> getNotificationsByUser(Principal principal) {
        List<NotificacaoDto> notificacoes = notificacaoService.getNotificationsByUser(principal.getName());
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/type/{type_id}")
    public ResponseEntity<List<NotificacaoDto>> getNotificationsByType(@PathVariable("type_id") Long typeId,
            Principal principal) {
        List<NotificacaoDto> notificacoes = notificacaoService.getNotificationsByType(typeId, principal.getName());
        return ResponseEntity.ok(notificacoes);
    }
}
