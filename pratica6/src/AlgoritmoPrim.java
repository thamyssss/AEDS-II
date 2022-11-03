import matrizadj.XGrafo;

// Implementa o algoritmo de Prim, cujo grafo de entrada G e fornecido atraves do construtor
public class AlgoritmoPrim {
    private int antecessor[]; // Armazena o antecessor de v na arvore
    private double p[];
    private double pesoTotal;
    private XGrafo grafo;

    public AlgoritmoPrim(XGrafo grafo) {
        this.grafo = grafo;
        this.pesoTotal = 0;
    }

    public void obterAgm(int raiz) throws Exception { // Recebe o vertice raiz como entrada
        int n = this.grafo.numVertices();
        this.p = new double[n]; // Peso dos vertices
        int vs[] = new int[n + 1]; // Vertices
        boolean itensHeap[] = new boolean[n];
        this.antecessor = new int[n];

        for (int u = 0; u < n; u++) {
            this.antecessor[u] = -1;
            p[u] = Double.MAX_VALUE; // Infinito
            vs[u + 1] = u; // Heap indireto a ser construido
            itensHeap[u] = true;
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();

        while (!heap.vazio()) {
            int u = heap.retiraMin();
            itensHeap[u] = false;

            if (!this.grafo.listaAdjVazia(u)) {
                XGrafo.Aresta adj = grafo.primeiroListaAdj(u); // Cria um novo grafo

                while (adj != null) {
                    int v = adj.v2();
                    if (itensHeap[v] && (adj.peso() < this.peso(v))) {
                        antecessor[v] = u; // Salva os vertices antecessores
                        heap.diminuiChave(v, adj.peso());
                    }
                    adj = grafo.proxAdj(u);
                }
            }
        }
    }

    public int antecessor(int u) { // Obtem o antecessor de um certo vertice
        return this.antecessor[u];
    }

    public double peso(int u) { // Obtem o o peso associado a um vertice
        return this.p[u];
    }

    public double getPeso() {
        for (int u = 0; u < this.p.length; u++)
            if (this.antecessor[u] != -1)
                this.pesoTotal += peso(u);

        return this.pesoTotal;
    }

    public void imprime() { // Imprime as arestas da arvore
        for (int u = 0; u < this.p.length; u++)
            if (this.antecessor[u] != -1)
                System.out.println("(" + antecessor[u] + "," + u + ") -- peso: " + peso(u));
    }
}
