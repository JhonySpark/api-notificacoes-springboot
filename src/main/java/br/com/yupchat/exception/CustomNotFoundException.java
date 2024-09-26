package br.com.yupchat.exception;

public class CustomNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public static final String USER_NOT_FOUND = "Usuario não encontrato";
	public static final String NOTIFICAO_NOT_FOUND = "Notificação não encontrada";
	public static final String TIPO_NOTIFICACAO_NOT_FOUND = "Tipo de notificação não encontrado";

    public CustomNotFoundException(String message) {
        super(message);
    }
}
