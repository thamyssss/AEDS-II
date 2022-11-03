package matrizadj;

public class XCiclo {
    public static final byte branco = 0; // Ainda nao foi visitado
    public static byte cinza = 1; // Ja foi visitado mas ainda nao foi processado
    public static byte preto = 2; // Ja foi visitado e processado

    private int d[]; // tempo de descoberta
    private int t[]; // tempo de termino do exame da lista de adjacentes de v
    private int antecessor[];
    private XGrafo grafo;
    private Boolean ciclo;

    public XCiclo(XGrafo grafo) {
        this.grafo = grafo;
        int n = this.grafo.numVertices();
        d = new int[n];
        t = new int[n];
        antecessor = new int[n];
        this.ciclo = false;
    }

    private int visitaDfs(int u, int tempo, int cor[]) {
        cor[u] = cinza; // Primeira visita ao vertice
        this.d[u] = ++tempo; // Incrementa 1 no tempo de descoberta

        if (!this.grafo.listaAdjVazia(u)) { // Entra na aresta
            XGrafo.Aresta a = this.grafo.primeiroListaAdj(u);
            while (a != null) {
                int v = a.v2();
                if (cor[v] == branco) { // aciclico
                    this.antecessor[v] = u;
                    tempo = this.visitaDfs(v, tempo, cor);
                }

                if (cor[v] == cinza) // ciclico
                    this.ciclo = true;
                a = this.grafo.proxAdj(u);
            }
        }
        cor[u] = preto;
        this.t[u] = ++tempo;
        return tempo;
    }

    // Metodo que faz a busca em profundidade
    public void BuscaProfundidade() {
        int tempo = 0;
        int cor[] = new int[this.grafo.numVertices()];
        for (int u = 0; u < grafo.numVertices(); u++) {
            cor[u] = branco; // define todas como branco
            this.antecessor[u] = -1;
        }
        for (int u = 0; u < grafo.numVertices(); u++)
            if (cor[u] == branco)
                tempo = this.visitaDfs(u, tempo, cor);
    }

    public int d(int v) {
        return this.d[v];
    }

    public int t(int v) {
        return this.t[v];
    }

    public int antecessor(int v) {
        return this.antecessor[v];
    }

    public Boolean getCiclo() {
        this.BuscaProfundidade();
        return this.ciclo;
    }

}