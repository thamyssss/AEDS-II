package Arvore;
import Item.Item;

public class TestaSBBOrdenado {    
    public static void main(String[] args) {
        ArvoreSBB arvore = new ArvoreSBB();
        Item x;
        int n = 10000; // Maximo de elementos da arvore
        
        // Gera 9 arvores diferentes
        for(int j = 1; j < 10; j++) {
            System.out.println("Arvore "+ j);
            
            // Insere cada chave na arvore e testa sua integridade apos cada insercao
            for(int i = 0; i < n; i++) {
                x = new Item(i);
                arvore.insere(x);
                arvore.testa();
            }

            // Procura um elemento inexistente
            x = new Item(200000);
        
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
}
