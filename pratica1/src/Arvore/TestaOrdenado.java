package Arvore;
import Item.Item;

public class TestaOrdenado {    
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Item x;
        System.out.println("Arvore 4");
        int n = 4000; // Maximo de elementos da arvore

        // Insere cada chave na arvore e testa sua integridade apos cada insercao
        for(int i = 0; i < n; i++) {
            x = new Item(i);
            arvore.insere(x);
            arvore.testa();
        }

        // Procura um elemento inexistente
        x = new Item(20000);
        
        // Recebe o n de comparacoes realizadas
        long tempoI = System.nanoTime();
        int comparacoes = arvore.pesquisa(x);
        long tempoF = System.nanoTime();
        System.out.println("Total de comparacoes: "+ comparacoes);

        // Mede o tempo gasto na pesquisa
        long tempoGasto = tempoF - tempoI;
        System.out.printf("Tempo total gasto: %d nanosegundos\n", tempoGasto);
    }
}
