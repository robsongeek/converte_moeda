import moeda.ConverteMoeda;
import moeda.MoedaRecord;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    private static void converterMoeda(double taxaOrigem, double taxaDestino, double valor,  String origem, String destino) {
        double valorConvertido = valor * (taxaDestino / taxaOrigem);
        System.out.println("***********************************************************");
        System.out.println("\u001B[35mO valor " +valor+ " " +origem+ " corresponde ao valor final de " +valorConvertido+" "+destino+".\u001B[0m" );
        System.out.println("***********************************************************");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConverteMoeda converte = new ConverteMoeda();

        // Busca as taxas uma única vez com base em USD
        MoedaRecord moeda = converte.buscaMoeda("USD");
        Map<String, Double> taxas = moeda.conversion_rates();

        System.out.println("""
                *******************************************************
                          PROGRAMA PARA CONVERSÃO DE MOEDAS
                *******************************************************
                """);

        while (true) {
            System.out.println("""
                    1 - Real Brasileiro ==> Dólar Americano
                    2 - Dólar Americano ==> Real Brasileiro
                    3 - Euro ==> Real Brasileiro
                    4 - Real Brasileiro ==> Euro
                    5 - Iene Japonês ==> Real Brasileiro
                    6 - Real Brasileiro ==> Iene Japonês
                    7 - Real Brasileiro ==> Dólar Australiano
                    0 - Sair
                    """);
            System.out.print("\u001B[32m⏳Por favor, escolha uma opção (1-7): \u001B[0m");
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.print("""
                        \nDeseja realmente sair?
                        0 - Cancelar e voltar ao menu
                        1 - Confirmar saída
                        Digite a opção:\s""");
                int confirmacao = scanner.nextInt();

                if (confirmacao == 1) {
                    System.out.println("\u001B[33m\nEncerrando sistema...\u001B[0m");
                    break;
                } else {
                    System.out.println("\u001B[33m\n⚠️ Operação cancelada. Retornando ao menu principal...\u001B[0m");
                    continue;
                }
            }

            String origem = "";
            String destino = "";

            switch (opcao) {
                case 1 -> { origem = "BRL"; destino = "USD"; }
                case 2 -> { origem = "USD"; destino = "BRL"; }
                case 3 -> { origem = "EUR"; destino = "BRL"; }
                case 4 -> { origem = "BRL"; destino = "EUR"; }
                case 5 -> { origem = "JPY"; destino = "BRL"; }
                case 6 -> { origem = "BRL"; destino = "JPY"; }
                case 7 -> { origem = "BRL"; destino = "AUD"; }
                default -> {
                    System.out.println("\u001B[33mOpção inválida!\u001B[0m");
                    continue;
                }
            }

            System.out.print("\u001B[32m⏳Por favor, digite o valor a converter: \u001B[0m");
            double valorConverter = scanner.nextDouble();

            try {
                double taxaOrigem = taxas.get(origem);
                double taxaDestino = taxas.get(destino);
                converterMoeda(taxaOrigem, taxaDestino, valorConverter, origem, destino);
            } catch (NullPointerException e) {
                System.out.println("Erro: Moeda não encontrada nas taxas de câmbio");
            }
        }
        scanner.close();
    }
}