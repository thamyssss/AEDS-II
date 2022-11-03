package Heuristica;

import Grafo.Grafo;

public class HeuristicaGulosa {
    private Grafo grafo;
    private boolean[] visitas; // armazena as visitas a um dos vertices do grafo
    private int[][] melhorCaminho;
    private int menorDistancia = 0;

    public HeuristicaGulosa(Grafo grafo) {
        int numVertices = grafo.numVertices();

        this.grafo = grafo;
        this.visitas = new boolean[numVertices];
        this.melhorCaminho = new int[grafo.numVertices()][3];

        for (int i = 0; i < numVertices; i++) {
            this.visitas[i] = false;
        }
    }

    public boolean visitouTodos(boolean[] visitas) {
        boolean result = true;
        for (boolean vertice : visitas) {
            if (!vertice) {
                result = false;
            }
        }
        return result;
    }

    public int[][] encontraCaminho() {
        int vi = 0, verticeX, pesoV, i = 0;
        visitas[0] = true;
        for (; visitouTodos(visitas) == false; i++) {
            verticeX = grafo.menorListaAdjacencia(vi, visitas).v2();
            pesoV = grafo.menorListaAdjacencia(vi, visitas).peso();

            melhorCaminho[i][0] = vi;
            melhorCaminho[i][1] = verticeX;
            melhorCaminho[i][2] = pesoV;

            menorDistancia += pesoV;
            vi = verticeX;
            visitas[verticeX] = true;
        }
        visitas[0] = false;

        verticeX = grafo.menorListaAdjacencia(vi, visitas).v2();
        pesoV = grafo.menorListaAdjacencia(vi, visitas).peso();

        melhorCaminho[i][0] = vi;
        melhorCaminho[i][1] = verticeX;
        melhorCaminho[i][2] = pesoV;

        menorDistancia += pesoV;

        return melhorCaminho;
    }

    public int getPesoTotal() {
        return menorDistancia;
    }
}
