public abstract class ContaBancaria implements Bloqueavel {
    private final String numero;
    private final String titular;
    protected double saldo;
    private boolean ativa;

    public ContaBancaria(String numero, String titular, double saldoInicial) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular nao pode ser nulo ou vazio.");
        }

        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Numero da conta nao pode ser nulo ou vazio.");
        }

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial nao pode ser negativo.");
        }

        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    public abstract double calcularRendimento();

    public void depositar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva("deposito");

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de deposito deve ser maior que zero.");
        }

        saldo += valor;
    }

    public void sacar(double valor) throws OperacaoInvalidaException {
        validarContaAtiva("saque");
        validarValorSaque(valor);

        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.", saldo, valor);
        }

        saldo -= valor;
    }

    protected void validarContaAtiva(String operacao) throws OperacaoInvalidaException {
        if (!ativa) {
            throw new OperacaoInvalidaException(
                    "Nao e possivel realizar " + operacao + " em conta bloqueada.",
                    operacao,
                    numero
            );
        }
    }

    protected void validarValorSaque(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser maior que zero.");
        }
    }

    @Override
    public void bloquear() {
        ativa = false;
    }

    @Override
    public void desbloquear() {
        ativa = true;
    }

    @Override
    public boolean isAtiva() {
        return ativa;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {numero='" + numero + '\''
                + ", titular='" + titular + '\''
                + ", saldo=" + String.format("%.2f", saldo)
                + ", ativa=" + ativa
                + '}';
    }
}
