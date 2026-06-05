public class ContaPoupanca extends ContaBancaria implements Tributavel {
    private static final double TAXA_RENDIMENTO_MENSAL = 0.005;
    private static final double ALIQUOTA_IMPOSTO_RENDIMENTO = 0.225;
    private static final int DIAS_MINIMOS_ENTRE_SAQUES = 30;

    private int diasDesdeUltimoSaque;

    public ContaPoupanca(String numero, String titular, double saldoInicial, int diasDesdeUltimoSaque) {
        super(numero, titular, saldoInicial);
        if (diasDesdeUltimoSaque < 0) {
            throw new IllegalArgumentException("Dias desde o ultimo saque nao pode ser negativo.");
        }
        this.diasDesdeUltimoSaque = diasDesdeUltimoSaque;
    }

    @Override
    public double calcularRendimento() {
        return saldo * TAXA_RENDIMENTO_MENSAL;
    }

    @Override
    public double calcularImposto() {
        return calcularRendimento() * ALIQUOTA_IMPOSTO_RENDIMENTO;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (diasDesdeUltimoSaque < DIAS_MINIMOS_ENTRE_SAQUES) {
            throw new IllegalArgumentException("Conta poupanca exige minimo de 30 dias entre saques.");
        }

        super.sacar(valor);
        diasDesdeUltimoSaque = 0;
    }

    public int getDiasDesdeUltimoSaque() {
        return diasDesdeUltimoSaque;
    }

    public void setDiasDesdeUltimoSaque(int diasDesdeUltimoSaque) {
        if (diasDesdeUltimoSaque < 0) {
            throw new IllegalArgumentException("Dias desde o ultimo saque nao pode ser negativo.");
        }
        this.diasDesdeUltimoSaque = diasDesdeUltimoSaque;
    }
}
