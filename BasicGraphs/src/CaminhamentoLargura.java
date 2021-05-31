import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class CaminhamentoLargura {
    private boolean marked[];
    private int edgeTo[];
    private int distTo[];
    private int s;
    private Graph g;

    public CaminhamentoLargura(Graph g, int s) {
        this.s = s;
        this.g = g;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        bfs(s);
    }

    private void bfs(int s) {
        List<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        edgeTo[s] = -1; // ninguém antes
        // System.out.println("Estou no " + s);
        while (!q.isEmpty()) {
            int v = q.remove(0);
            // System.out.println("Estou no " + v);
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.add(w);
                }
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

    public void exibe() {
        for (int v = 0; v < g.V(); v++) {
            System.out.println(v + ": " + marked[v] + " - " + edgeTo[v] + " - " + distTo[v]);
        }
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
        CaminhamentoLargura bfs = new CaminhamentoLargura(g, 0);
        bfs.exibe();
        System.out.println();
        /**/
        for (int v = 0; v < g.V(); v++) {
            System.out.println("Tem caminho até " + v + "? " + bfs.hasPath(v));
            if (bfs.hasPath(v)) {
                for (int w : bfs.pathTo(v)) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }
    }
}
