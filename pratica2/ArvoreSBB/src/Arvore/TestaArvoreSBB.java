package Arvore;
import java.util.Random;
import Item.Item;

public class TestaArvoreSBB {
    public static void main (String[] args) {
        ArvoreSBB arvore = new ArvoreSBB();
        Item x;
        int n = 10000; // Maximo de elementos da arvore

        //instancia um objeto da classe Random usando o construtor padrão
        Random gerador = new Random();
        
        // Gera 9 arvores diferentes
        for(int j = 1; j < 10; j++) {
            System.out.println("Arvore "+ j);
            
            // Imprime sequencia de n numeros inteiros aleatorios de 0 a 2000
            for (int i = 0; i < n; i++) {
                
                int numAleatorio = gerador.nextInt(2001);
                
                // Insere cada chave na arvore e testa sua integridade apos cada insercao
                x = new Item(numAleatorio);
                arvore.insere(x);
            }
            
            // Procura um elemento inexistente
            x = new Item(200000);
            
            // Recebe o numero de comparacoes realizadas
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
