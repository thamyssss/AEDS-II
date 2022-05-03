package Arvore;
import java.io.*;

import Item.Item;

// Programa para criar a arvore
public class CriaArvore {
    public static void main ( String [ ] args) throws Exception {
        ArvoreBinaria dicionario = new ArvoreBinaria();
        
        BufferedReader in = new BufferedReader (
            new InputStreamReader (System.in));
    
        int chave = Integer.parseInt (in.readLine());
        
        while (chave > 0) {
            Item item = new Item(chave);
            dicionario.insere(item);
            chave = Integer.parseInt (in.readLine());
        }
    }
}
