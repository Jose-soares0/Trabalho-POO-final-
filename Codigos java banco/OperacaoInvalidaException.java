public class OperacaoInvalidaException extends Exception {
    private final String operacao;
    private final String numeroConta;

    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
        this.operacao = "";
        this.numeroConta = "";
    }

    public OperacaoInvalidaException(String mensagem, String operacao, String numeroConta) {
        super(mensagem);
        this.operacao = operacao;
        this.numeroConta = numeroConta;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getNumeroConta() {
        return numeroConta;
    }
}
