import java.util.ArrayList;

public class CaminhamentoProfundidade {

    private boolean[] marked;
    private int[] edgeTo;
    private int s; // vértice inicial

    public CaminhamentoProfundidade(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        System.out.println("Visitando " + v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) { // ainda não visitei?
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
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
        In in = new In(args[0]);
        Graph g = new Graph(in);
        // StdOut.println(g);
        // StdOut.println();
        // StdOut.println(g.toDot());
        // Graph g = new Graph(4);
        // g.addEdge(0, 1);
        // g.addEdge(1, 2);
        // g.addEdge(0, 3);
        CaminhamentoProfundidade dfs = new CaminhamentoProfundidade(g, 0);
        for (int v = 0; v < g.V(); v++) {
            System.out.println("Tem caminho até " + v + "? " + dfs.hasPath(v));
            if (dfs.hasPath(v)) {
                for (int w : dfs.pathTo(v)) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }
    }

}
