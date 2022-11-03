package matrizadj;

public class XGrafo {
    // Classe aresta com os atributos v1 e v2
    public static class Aresta {
        private int v1, v2;
        private int peso;

        // Construtor para inicializar os atributos
        public Aresta(int v1, int v2, int peso) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = peso;
        }

        // Metodos para obter o valor dos atributos
        public int v1() {
            return this.v1;
        }

        public int v2() {
            return this.v2;
        }

        public int peso() {
            return this.peso;
        }
    }

    private int mat[][]; // pesos das arestas
    private int numVertices; // atributo indicando o numero de vertices
    private int pos[]; // Atributo utilizado para percorrer a lista de adjacencia de um dado vertice

    public XGrafo(int numVertices) {
        // Instacializacao de mat e pos
        this.mat = new int[numVertices][numVertices];
        this.pos = new int[numVertices];
        this.numVertices = numVertices;

        // inicializacao de mat e pos
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++)
                this.mat[i][j] = 0;
            this.pos[i] = -1;
        }
    }

    public XGrafo(int numVertices, int numArestas) {
        this.mat = new int[numVertices][numVertices];
        this.pos = new int[numVertices];
        this.numVertices = numVertices;

        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++)
                this.mat[i][j] = 0;
            this.pos[i] = -1;
        }
    }

    // Insercao de uma nova aresta em mat[][]
    public void insereAresta(int v1, int v2, int peso) {
        this.mat[v1][v2] = peso;
    }

    // Verifica se existe uma aresta cujas incidencias são passadas por parametro
    public boolean existeAresta(int v1, int v2) {
        return (this.mat[v1][v2] > 0);
    }

    public boolean listaAdjVazia(int v) {
        for (int i = 0; i < this.numVertices; i++)
            if (this.mat[v][i] > 0)
                return false;
        return true;
    }

    // Retorna o primeiro objeto Aresta em que o parametro v participa
    public Aresta primeiroListaAdj(int v) {
        // Retorna a primeira aresta que o vertice v participa ou null se a lista de
        // adjacencia v for vazia
        this.pos[v] = -1;
        // this.pos[v] = -1 -> inicializa o percurso das adjacencias de v
        return this.proxAdj(v);
    }

    public Aresta proxAdj(int v) {
        // Posiciona p[] na proxima aresta a ser pesquisada
        this.pos[v]++;

        // Verifica se p[v] e uma adjacencia valida e se a djacencia existe, se nao
        // existir verifica a proxima
        while ((this.pos[v] < this.numVertices) && (this.mat[v][this.pos[v]] == 0))
            this.pos[v]++;
        // Se a adjacencia for invalida retorna null
        if (this.pos[v] == this.numVertices) {
            return null;
        }
        // Caso contrario, retorna um objeto Aresta com a aresta procurada
        else
            return new Aresta(v, this.pos[v], this.mat[v][this.pos[v]]);
    }

    public Aresta retiraAresta(int v1, int v2) {
        if (this.mat[v1][v2] == 0) {
            return null; // Aresta nao existe
        } else {
            Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2]);
            this.mat[v1][v2] = 0;
            return aresta;
        }
    }

    public void imprime() {
        System.out.print("  ");
        for (int i = 0; i < this.numVertices; i++)
            System.out.print(i + " ");
        System.out.println();

        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.numVertices; j++)
                System.out.print(this.mat[i][j] + "  ");
            System.out.println();
        }
    }

    public int numVertices() {
        return this.numVertices;
    }
}