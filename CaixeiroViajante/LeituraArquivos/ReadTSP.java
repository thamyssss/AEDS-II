package LeituraArquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Grafo.Grafo;

public class ReadTSP {

    private Scanner leitor;
    private int[][] matriz;
    private Grafo grafo;
    public int tamanho;

    public ReadTSP(String file) throws FileNotFoundException, IOException {
        this.leitor = new Scanner(new File("./Arquivos/" + file + ".tsp")); // abrindo o arquivo

        // lendo algumas informacoes do arquivo .tsp
        while (!leitor.next().equals("DIMENSION:"))
            ;
        tamanho = Integer.parseInt(leitor.next());

        while (!leitor.next().equals("EDGE_WEIGHT_SECTION"))
            ;

        matriz = geraMatriz(file);
        grafo = geraGrafo(file);
    }

    public int[][] geraMatriz(String file) {
        int[][] matriz = new int[tamanho][tamanho];
        int linha = -1;
        int coluna = -1;

        if (file.equals("pa561")) { // arquivo que a diagonal e inferior
            linha = 0;
            coluna = 0;
            while (leitor.hasNext()) {
                String str = leitor.next();
                if (str.equals("DISPLAY_DATA_SECTION"))
                    break;
                if (str.equals("0")) {
                    matriz[linha][coluna] = Integer.parseInt(str);
                    linha++;
                    coluna = 0;
                } else {
                    matriz[linha][coluna] = Integer.parseInt(str);
                    coluna++;
                }
            }
        } else { // arquivo que a diagonal e superior
            while (leitor.hasNext()) {
                String str = leitor.next();
                if (str.equals("EOF"))
                    break;
                if (str.equals("0")) {
                    linha++;
                    coluna = linha;
                    matriz[linha][coluna] = Integer.parseInt(str);
                } else {
                    coluna++;
                    matriz[linha][coluna] = Integer.parseInt(str);
                }
            }
        }
        return matriz;
    }

    public Grafo geraGrafo(String file) { // gerar o grafo a partir do arquivo
        Grafo grafo = new Grafo(tamanho);
        if (file.equals("pa561")) { // arquivo que a diagonal e inferior
            for (int i = tamanho - 1; i >= 0; i--) {
                for (int j = 0; j < i; j++)
                    grafo.insereArestaBidirecionada(i, j, matriz[i][j]);
            }
        } else { // arquivo que a diagonal e superior
            for (int i = 0; i < tamanho; i++) {
                for (int j = i; j < tamanho; j++)
                    grafo.insereArestaBidirecionada(i, j, matriz[i][j]);
            }
        }
        return grafo;
    }

    public Grafo getGrafo() {
        return grafo;
    }
}
