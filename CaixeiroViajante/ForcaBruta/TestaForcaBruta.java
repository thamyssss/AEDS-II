package ForcaBruta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import Grafo.Grafo;

public class TestaForcaBruta {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void criaGrafo(Grafo grafo, int numVertices, int i, int j, int a, int b) {
        Random rand = new Random();
        int n = rand.nextInt(b - a) + a; // numero aleatorio entre a e b
        if (i == numVertices) {
            return;
        } else if (j < numVertices - 1) {
            grafo.insereArestaBidirecionada(i, j, n);
            j++;
            criaGrafo(grafo, numVertices, i, j, a, b);
        } else if (j == numVertices - 1) {
            grafo.insereArestaBidirecionada(i, j, n);
            i++;
            j = i + 1;
            criaGrafo(grafo, numVertices, i, j, a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        long time0, time1;
        System.out.print("Numero de cidades (vertices): ");
        int numVertices = Integer.parseInt(in.readLine());
        Grafo grafo = new Grafo(numVertices);

        System.out.print("Distancias aleatorias \nde: ");
        int a = Integer.parseInt(in.readLine());
        System.out.print("ate: ");
        int b = Integer.parseInt(in.readLine());
        System.out.println();

        criaGrafo(grafo, numVertices, 0, 1, a, b);
        System.out.println("Grafo gerado: ");
        grafo.imprime();

        ForcaBruta f = new ForcaBruta(grafo);
        System.out.println("\nCidade inicial (ponto de partida): ");
        int pointA = Integer.parseInt(in.readLine());
        time0 = System.nanoTime();
        f.backtracking(pointA);
        f.solucaoOtima();
        time1 = System.nanoTime();

        NumberFormat formatter = new DecimalFormat("#0.00000");

        System.out.println(
                "\nTempo de execucao: " +
                        formatter.format((time1 - time0) * Math.pow(10, -9)) +
                        " segundos\n");
    }
}