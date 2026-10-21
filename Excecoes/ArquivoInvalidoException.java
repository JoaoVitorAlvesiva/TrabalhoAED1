package TrabalhoCandidato.Excecoes;

public class ArquivoInvalidoException extends RuntimeException {
	public ArquivoInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	public ArquivoInvalidoException() {
		super("Arquivo inválido");
	}
}