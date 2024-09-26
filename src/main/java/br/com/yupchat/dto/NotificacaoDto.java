package br.com.yupchat.dto;

public class NotificacaoDto {
	private Long id;
    private String titulo;
    private String descricao;
    private String corpo;
    private String imagem;
    private Long idTipoNotificacao;
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCorpo() {
        return corpo;
    }
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
    public String getimagem() {
        return imagem;
    }
    public void setimagem(String imagem) {
        this.imagem = imagem;
    }
    public Long getidTipoNotificacao() {
        return idTipoNotificacao;
    }
    public void setidTipoNotificacao(Long idTipoNotificacao) {
        this.idTipoNotificacao = idTipoNotificacao;
    }
	public final Long getId() {
		return id;
	}
	public final void setId(Long id) {
		this.id = id;
	}
}
