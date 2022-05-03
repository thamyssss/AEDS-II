package Arvore;
import Item.Item;

// Para inicializar o no raiz
public class ArvoreBinaria {
    private static class No {
        Item reg;
        No esq, dir;
    }
    
    private No raiz;
    private int comparacoes = 0; // Numero de comparacoes feita na pesquisa

    //Caminhamento central e impressao da arvore
    private void central (No p) {
        if (p != null) {
            central (p.esq);
            System.out.println (p.reg.getChave());
            central (p.dir);
        }
    }    

    // Metodo para pesquisar na arvore -> encontrar um registro com uma chave reg
    private Item pesquisa (Item reg, No p) {
        if (p == null) return null; //Registro nao encontrado
        
        else if (reg.compara (p.reg) < 0) { //compara com a chave que esta na raiz, se e menor -> procura na subarvore esquerda
            comparacoes++;
            return pesquisa (reg, p.esq);
        }
        
        else if (reg.compara (p.reg) > 0) { //se e maior -> procura na subarvore direita
            comparacoes++;
            return pesquisa (reg, p.dir);
        }
        
        else return p.reg; //se a pesquisa tiver sucesso a chave passada em reg e retornada
    }

    // Procedimento para inserir na arvore
    private No insere (Item reg, No p) {
        
        if (p == null) { //a referencia null e atingida no ponto de insercao
            p = new No (); p.reg = reg;
            p.esq = null; p.dir = null;
        }
        else if (reg.compara (p.reg) < 0) //se o novo elemento e menor, insere a direita
            p.esq = insere (reg, p.esq);
        
        else if (reg.compara (p.reg) > 0) //se e maior, a direita
            p.dir = insere (reg, p.dir);
        
        //else System.out.println ("Erro: Registro ja existente"); //se o numero ja existe na arvore nao insere-o novamente
        
        return p;
    }

    // Metodo para testar o funcionamento da classe
    private void testa (No p) { 
        if (p == null) return;
        
        if (p.esq != null) { 
            if (p.reg.compara (p.esq.reg) < 0) { 
                System.out.println ("Erro: Pai "+ p.reg.toString() + "menor que filho a esquerda" + p.esq.reg.toString());
                System.exit(1);
            }
        }
        if (p.dir != null) { 
            
            if (p.reg.compara (p.dir.reg) > 0) { 
                System.out.println ("Erro: Pai " + p.reg.toString() + "maior que filho a direita" + p.dir.reg.toString());
                System.exit(1);
            }
        }
        testa(p.esq);
        testa(p.dir);
    }
    
    public ArvoreBinaria() {
        this.raiz = null;
    }
    public int pesquisa (Item reg) {
        this.pesquisa (reg, this.raiz);
        return this.comparacoes; // Retorna o numero de comparacoes
    }
    public void insere (Item reg) {
        this.raiz = this.insere (reg, this.raiz);
    }
    public void imprime () {
        this.central (this.raiz);
    }
    public void testa () { 
        this.testa (this.raiz);
    }
}

