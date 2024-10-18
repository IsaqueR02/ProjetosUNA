public class AgenteCustoBeneficio {

    public static void main(String[] args) {
        // Definição dos produtos: Preço e Volume em mililitros (ml)
        double[] precos = {65.00, 105.00, 330.00};
        double[] volumes = {500, 750, 3000};
        String[] produtos = {"Azeite de 500 ml", "Azeite de 750 ml", "Azeite de 3 litros"};

        // Cálculo do custo por ml para cada produto
        double menorCustoPorMl = Double.MAX_VALUE;
        double maiorCustoPorMl = 0;
        String melhorProduto = "";
        String piorProduto = "";

        for (int i = 0; i < precos.length; i++) {
            double custoPorMl = precos[i] / volumes[i];
            System.out.println(produtos[i] + " - Custo por ml: R$ " + custoPorMl);

            // Encontrar o produto com o menor custo por ml (melhor escolha)
            if (custoPorMl < menorCustoPorMl) {
                menorCustoPorMl = custoPorMl;
                melhorProduto = produtos[i];
            }

            // Encontrar o produto com o maior custo por ml (pior escolha)
            if (custoPorMl > maiorCustoPorMl) {
                maiorCustoPorMl = custoPorMl;
                piorProduto = produtos[i];
            }
        }

        // Exibir o melhor e pior custo-benefício
        System.out.println("\nMelhor opção de compra: " + melhorProduto + " com custo por ml de R$ " + menorCustoPorMl);
        System.out.println("Pior opção de compra: " + piorProduto + " com custo por ml de R$ " + maiorCustoPorMl);
    }
}