public class ContaSalario extends ContaBancaria {
    private boolean saqueRealizadoNoMes;

    public ContaSalario(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        this.saqueRealizadoNoMes = false;
    }

    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (saqueRealizadoNoMes) {
            throw new IllegalArgumentException("Conta salario permite no maximo 1 saque por mes.");
        }

        super.sacar(valor);
        saqueRealizadoNoMes = true;
    }

    public boolean isSaqueRealizadoNoMes() {
        return saqueRealizadoNoMes;
    }

    public void iniciarNovoMes() {
        saqueRealizadoNoMes = false;
    }
}
