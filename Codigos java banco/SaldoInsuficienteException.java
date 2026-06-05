public class SaldoInsuficienteException extends RuntimeException {
    private final double saldoAtual;
    private final double valorSolicitado;

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
        this.saldoAtual = 0.0;
        this.valorSolicitado = 0.0;
    }

    public SaldoInsuficienteException(String mensagem, double saldoAtual, double valorSolicitado) {
        super(mensagem);
        this.saldoAtual = saldoAtual;
        this.valorSolicitado = valorSolicitado;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }
}
