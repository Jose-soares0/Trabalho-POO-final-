import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContaCorrente corrente = new ContaCorrente("001", "Ana Silva", 500.00);
        ContaPoupanca poupanca = new ContaPoupanca("002", "Bruno Lima", 2000.00, 35);
        ContaSalario salario = new ContaSalario("003", "Carla Sousa", 1200.00);

        List<ContaBancaria> contas = new ArrayList<>();
        contas.add(corrente);
        contas.add(poupanca);
        contas.add(salario);

        System.out.println("=== Polimorfismo: rendimentos ===");
        for (ContaBancaria conta : contas) {
            System.out.printf(
                    "%s - titular: %s - rendimento: R$ %.2f%n",
                    conta.getClass().getSimpleName(),
                    conta.getTitular(),
                    conta.calcularRendimento()
            );
        }

        System.out.println("\n=== Operacoes validas ===");
        try {
            corrente.sacar(300.00);
            poupanca.depositar(250.00);
            salario.sacar(500.00);

            System.out.printf("Imposto do ultimo saque da corrente: R$ %.2f%n", corrente.calcularImposto());
            System.out.printf("Imposto sobre rendimento da poupanca: R$ %.2f%n", poupanca.calcularImposto());
        } catch (OperacaoInvalidaException e) {
            System.out.println("Operacao bloqueada: " + e.getMessage());
        }

        System.out.println("\n=== Provocando SaldoInsuficienteException ===");
        try {
            poupanca.sacar(10000.00);
        } catch (SaldoInsuficienteException e) {
            System.out.printf(
                    "Erro tratado: %s Saldo atual: R$ %.2f. Valor solicitado: R$ %.2f.%n",
                    e.getMessage(),
                    e.getSaldoAtual(),
                    e.getValorSolicitado()
            );
        } catch (OperacaoInvalidaException e) {
            System.out.println("Operacao bloqueada: " + e.getMessage());
        }

        System.out.println("\n=== Provocando OperacaoInvalidaException ===");
        try {
            corrente.bloquear();
            corrente.depositar(100.00);
        } catch (OperacaoInvalidaException e) {
            System.out.printf(
                    "Erro tratado: %s Operacao: %s. Conta: %s.%n",
                    e.getMessage(),
                    e.getOperacao(),
                    e.getNumeroConta()
            );
        }
    }
}
