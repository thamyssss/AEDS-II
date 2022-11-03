package ForcaBruta;

import java.util.ArrayList;
import java.util.HashMap;

import Grafo.*;
import Grafo.Grafo.*;

public class ForcaBruta {
    private Grafo grafo;
    private HashMap<Integer, ArrayList<Integer>> mapa; // relaciona o vertice que visitou com a distancia total que
                                                       // percorreu para visita-lo
    private int menorDistancia = 0;

    public ForcaBruta(Grafo grafo) {
        this.grafo = grafo;
        this.mapa = new HashMap<>();
    }

    public void backtracking(int pointA) {
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pointA);

        // caminha de A ate o vertice
        for (int i = 0; i < arestasAdj.size(); i++) {
            int pointC = arestasAdj.get(i).v2();
            visita(visited, pointA, pointA, pointC);
        }
    }

    public ArrayList<Aresta> arestasAdj(int v) {
        ArrayList<Aresta> arestas = new ArrayList<>();
        for (int i = 0; i < this.grafo.numVertices(); i++)
            if (this.grafo.getPeso(v, i) > 0)
                arestas.add(new Aresta(v, i, this.grafo.getPeso(v, i)));
        return arestas;
    }

    private void visita(final ArrayList<Integer> visited, int pontoInicial, int pointA, int pointB) {
        int totalDistance;
        ArrayList<Aresta> arestasAdj = this.arestasAdj(pointA);
        ArrayList visitedCopy = new ArrayList(visited);
        visitedCopy.add(pointA);

        // salta o vertice visitado anteriormente e pula-o
        for (int i = 0; i < arestasAdj.size(); i++) {
            int pointC = arestasAdj.get(i).v2(); // vertice atual -> pointC
            if (!visitedCopy.contains(pointC)) {
                if (visitedCopy.size() == this.grafo.numVertices() - 1) {
                    visitedCopy.add(pointB);
                    visitedCopy.add(pontoInicial);
                    totalDistance = 0;
                    for (int j = 0; j < visitedCopy.size(); j++) {
                        if (j < visitedCopy.size() - 1)
                            totalDistance += this.grafo.getPeso(
                                    (int) visitedCopy.get(j),
                                    (int) visitedCopy.get(j + 1));
                    }
                    final ArrayList visitedCopy2 = new ArrayList(visitedCopy);
                    this.mapa.put(totalDistance, visitedCopy2);
                    visitedCopy.remove(visitedCopy.size() - 1);
                    visitedCopy.remove(visitedCopy.size() - 1);
                } else if (pointC != pointB) {
                    visita(visitedCopy, pontoInicial, pointC, pointB);
                }
            }
        }
    }

    public void solucaoOtima() {
        this.menorDistancia = this.mapa.keySet().stream().findFirst().get();

        for (Integer distance : this.mapa.keySet()) // menor distancia
            if (distance < this.menorDistancia)
                this.menorDistancia = distance;
        System.out.print("\nMenor rota: ");

        for (int j = 0; j < this.mapa.get(this.menorDistancia).size(); j++)
            System.out.print(this.mapa.get(this.menorDistancia).get(j) + " ");
        System.out.println("\nDistancia total: " + this.menorDistancia);
    }
}