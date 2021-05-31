import java.util.ArrayList;

public class DetectaCiclo {

    private boolean[] marked;
    private int[] edgeTo;
    private int s; // vértice inicial
    private boolean cycle;

    public DetectaCiclo(Graph g) {
        this.s = 0;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        cycle = false;
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                System.out.println("DFS: " + s);
                dfs(g, s);
            }
            if (cycle)
                break;
        }
    }

    private void dfs(Graph g, int v) {
        // System.out.println("Visitando " + v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) { // ainda não visitei?
                edgeTo[w] = v;
                dfs(g, w);
            } else {
                // Se este não for o vértice por onde cheguei aqui...
                if (edgeTo[v] != w) {
                    cycle = true; // achamos um ciclo
                    return;
                }
            }
        }
    }

    public boolean hasCycle() {
        return cycle;
    }

    public boolean hasPath(int v) {
        return marked[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        if (!hasPath(v)) // verifica se há caminho antes
            return null;
        ArrayList<Integer> path = new ArrayList<>();
        while (v != s) {
            path.add(0, v); // insere no início do arraylist
            v = edgeTo[v];
        }
        path.add(0, s); // adiciona o vértice inicial
        return path;
    }

    public static void main(String[] args) {
        // In in = new In(args[0]);
        // Graph g = new Graph(in);
        // StdOut.println(g);
        // StdOut.println();
        // StdOut.println(g.toDot());
        /**/
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        // g.addEdge(2, 3);
        g.addEdge(0, 3);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 6);
        /**/
        DetectaCiclo dfs = new DetectaCiclo(g);
        System.out.println("Tem ciclo? " + dfs.hasCycle());
    }
}
