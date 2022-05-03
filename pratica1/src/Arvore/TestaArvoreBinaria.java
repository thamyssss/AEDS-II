package Arvore;
import Item.Item;
import java.util.Random; 

public class TestaArvoreBinaria {
    public static void main (String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Item x;

        System.out.println("Arvore 6");

        //instancia um objeto da classe Random usando o construtor padrão
        Random gerador = new Random();
        
        int n = 6000; // Maximo de elementos da arvore
        
        // Imprime sequencia de n comparacoess inteiros aleatorios de 0 a 2000
        for (int i = 0; i < n; i++) {
            
            int numAleatorio = gerador.nextInt(2001);
            
            // Insere cada chave na arvore e testa sua integridade apos cada insercao
            x = new Item(numAleatorio);
            arvore.insere(x);
            arvore.testa();
        }
        
        // Procura um elemento inexistente
        x = new Item(3000);
        
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
