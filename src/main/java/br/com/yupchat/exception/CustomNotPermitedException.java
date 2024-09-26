package br.com.yupchat.exception;

public class CustomNotPermitedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String INVALID_CREDENTIALS = "Credenciais inválidas";
	public static final String PERMISSION_DENIED_NOTIFICACAO_EDIT = "Você não tem permissão para atualizar este tipo de notificação";
	public static final String PERMISSION_DENIED_NOTIFICACAO_DELETE = "Você não tem permissão para deletar esta notificação";
	public static final String PERMISSION_DENIED_TIPO_NOTIFICACAO_EDIT = "Você não tem permissão para atualizar este tipo de notificação";
	public static final String PERMISSION_DENIED_TIPO_NOTIFICACAO_DELETE = "Você não tem permissão para deletar este tipo de notificação";
    public static final String TIPO_NOTIFICACAO_REFERENCIADO = "Não é possível excluir o tipo de notificação porque ele está sendo referenciado por notificações";

	public CustomNotPermitedException(String message) {
		super(message);
	}
}
