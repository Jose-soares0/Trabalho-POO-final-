public class ContaCorrente extends ContaBancaria implements Tributavel {
    private static final double LIMITE_CHEQUE_ESPECIAL = 1000.00;
    private static final double ALIQUOTA_IOF_SAQUE = 0.0038;

    private double ultimoImpostoSaque;

    public ContaCorrente(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        this.ultimoImpostoSaque = 0.0;
    }

    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva("saque");
        validarValorSaque(valor);

        double imposto = valor * ALIQUOTA_IOF_SAQUE;
        double totalDebitado = valor + imposto;
        double limiteDisponivel = saldo + LIMITE_CHEQUE_ESPECIAL;

        if (totalDebitado > limiteDisponivel) {
            throw new SaldoInsuficienteException(
                    "Saldo e limite insuficientes para saque.",
                    limiteDisponivel,
                    totalDebitado
            );
        }

        saldo -= totalDebitado;
        ultimoImpostoSaque = imposto;
    }

    @Override
    public double calcularImposto() {
        return ultimoImpostoSaque;
    }

    public double getLimiteChequeEspecial() {
        return LIMITE_CHEQUE_ESPECIAL;
    }

    public double getUltimoImpostoSaque() {
        return ultimoImpostoSaque;
    }
}
