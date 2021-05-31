import java.util.HashMap;

// Implementa o algoritmo Graus de Separação, através de caminhamento
// em largura (BFS) num grafo não dirigido
public class AppDOS {
    public static void main(String args[]) {
        Graph g = new Graph(120000); // +- 119 mil nomes diferentes
        HashMap<String, Integer> dic = new HashMap<>();
        String dicRev[] = new String[120000];
        int totalNomes = 0;

        In arq = new In("movies.txt");
        String aux = "";
        while ((aux = arq.readLine()) != null) {
            String dados[] = aux.split("/");
            String filme = dados[0];
            dic.put(filme, totalNomes);
            dicRev[totalNomes] = filme;
            int numFilme = totalNomes;
            totalNomes++;
            // System.out.println(filme);
            // Verificar o elenco
            for (int pos = 1; pos < dados.length; pos++) {
                String nome = dados[pos];
                int numPessoa = -1;
                if (!dic.containsKey(nome)) {
                    // Novo nome
                    dic.put(nome, totalNomes);
                    dicRev[totalNomes] = nome;
                    numPessoa = totalNomes;
                    totalNomes++;
                } else {
                    numPessoa = dic.get(nome);
                    // System.out.println("Nome repetido: "+nome
                    // +" - "+numPessoa);
                }
                g.addEdge(numFilme, numPessoa);
            }
        }
        arq.close();
        System.out.println("Total nomes:" + totalNomes);
        System.out.println("Total arestas:" + g.E());

        // In entrada = new In(); // Dá para usar StdIn diretamente
        System.out.print("Origem:");
        String nomeOrigem = StdIn.readLine();
        System.out.print("Destino:");
        String nomeDest = StdIn.readLine();
        // System.out.println(g.toDot());
        if (!dic.containsKey(nomeOrigem)) {
            System.out.println("Erro: não existe " + nomeOrigem);
        } else if (!dic.containsKey(nomeDest)) {
            System.out.println("Erro: não existe " + nomeDest);
        } else {
            int vertOrigem = dic.get(nomeOrigem);
            int vertDest = dic.get(nomeDest);
            // Faz BFS a partir do vertice correspondente ao nome de origem
            CaminhamentoLargura bfs = new CaminhamentoLargura(g, vertOrigem);
            if (bfs.hasPath(vertDest)) {
                System.out.println("Há caminho!");
                // Se existe um caminho, exibe os nomes associados aos vertices
                for (int w : bfs.pathTo(vertDest)) {
                    System.out.println("Nome: " + dicRev[w]);
                }
            } else
                System.out.println("Não há caminho...");
        }
    }
}
