import java.util.*;

// Classe para representar o grafo com suas conexões e custos
class Grafo {
    private Map<String, List<No>> adjacencias = new HashMap<>();

    // Adiciona uma aresta entre dois nós com custo
    public void adicionarAresta(String origem, String destino, int custo) {
        adjacencias.putIfAbsent(origem, new ArrayList<>());
        adjacencias.get(origem).add(new No(destino, custo));
    }

    // Busca em Largura (BFS)
    public void buscaLargura(String inicio, String objetivo) {
        Queue<String> fila = new LinkedList<>();
        Map<String, String> caminho = new HashMap<>();
        fila.add(inicio);
        caminho.put(inicio, null);

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            if (atual.equals(objetivo)) {
                exibirCaminho(caminho, objetivo);
                return;
            }

            for (No vizinho : adjacencias.get(atual)) {
                if (!caminho.containsKey(vizinho.nome)) {
                    fila.add(vizinho.nome);
                    caminho.put(vizinho.nome, atual);
                }
            }
        }
    }

    // Busca em Profundidade (DFS)
    public void buscaProfundidade(String inicio, String objetivo) {
        Stack<String> pilha = new Stack<>();
        Map<String, String> caminho = new HashMap<>();
        pilha.push(inicio);
        caminho.put(inicio, null);

        while (!pilha.isEmpty()) {
            String atual = pilha.pop();

            if (atual.equals(objetivo)) {
                exibirCaminho(caminho, objetivo);
                return;
            }

            for (No vizinho : adjacencias.get(atual)) {
                if (!caminho.containsKey(vizinho.nome)) {
                    pilha.push(vizinho.nome);
                    caminho.put(vizinho.nome, atual);
                }
            }
        }
    }

    // Busca A* com heurística
    public void buscaAEstrela(String inicio, String objetivo, Map<String, Integer> heuristicas) {
        PriorityQueue<No> fila = new PriorityQueue<>(Comparator.comparingInt(no -> no.custo));
        Map<String, String> caminho = new HashMap<>();
        Map<String, Integer> custoG = new HashMap<>();
        fila.add(new No(inicio, heuristicas.get(inicio)));
        caminho.put(inicio, null);
        custoG.put(inicio, 0);

        while (!fila.isEmpty()) {
            No atual = fila.poll();

            if (atual.nome.equals(objetivo)) {
                exibirCaminho(caminho, objetivo);
                return;
            }

            for (No vizinho : adjacencias.get(atual.nome)) {
                int novoCustoG = custoG.get(atual.nome) + vizinho.custo;

                if (!custoG.containsKey(vizinho.nome) || novoCustoG < custoG.get(vizinho.nome)) {
                    custoG.put(vizinho.nome, novoCustoG);
                    int f = novoCustoG + heuristicas.get(vizinho.nome);
                    fila.add(new No(vizinho.nome, f));
                    caminho.put(vizinho.nome, atual.nome);
                }
            }
        }
    }

    // Função auxiliar para exibir o caminho
    private void exibirCaminho(Map<String, String> caminho, String objetivo) {
        List<String> resultado = new ArrayList<>();
        String atual = objetivo;

        while (atual != null) {
            resultado.add(atual);
            atual = caminho.get(atual);
        }

        Collections.reverse(resultado);
        System.out.println("Caminho: " + String.join(" -> ", resultado));
    }
}

// Classe para representar um nó no grafo
class No {
    String nome;
    int custo;

    public No(String nome, int custo) {
        this.nome = nome;
        this.custo = custo;
    }
}

public class BuscaEmGrafos {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Definindo as arestas (cidades e distâncias)
        grafo.adicionarAresta("A", "B", 5);
        grafo.adicionarAresta("A", "D", 1);
        grafo.adicionarAresta("B", "C", 2);
        grafo.adicionarAresta("B", "E", 2);
        grafo.adicionarAresta("C", "F", 6);
        grafo.adicionarAresta("D", "E", 7);
        grafo.adicionarAresta("E", "F", 4);
        grafo.adicionarAresta("F", "G", 3);

        // Definindo as heurísticas para a busca A*
        Map<String, Integer> heuristicas = new HashMap<>();
        heuristicas.put("A", 4);
        heuristicas.put("B", 3);
        heuristicas.put("C", 2);
        heuristicas.put("D", 3);
        heuristicas.put("E", 2);
        heuristicas.put("F", 1);
        heuristicas.put("G", 0); // Heurística de G é 0 pois é o objetivo

        System.out.println("Busca em Largura (BFS):");
        grafo.buscaLargura("A", "G");

        System.out.println("\nBusca em Profundidade (DFS):");
        grafo.buscaProfundidade("A", "G");

        System.out.println("\nBusca A*:");
        grafo.buscaAEstrela("A", "G", heuristicas);
    }
}
