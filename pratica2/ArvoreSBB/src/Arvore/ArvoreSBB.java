package Arvore;
import Item.Item;

public class ArvoreSBB {
    private static class No { 
      Item reg; 
      No esq, dir;
      byte incE, incD; // Indicam o tipo de referencia (horizontal ou vertical) que sai do no
    }
    private static final byte Horizontal = 0; 
    private static final byte Vertical = 1; // Representam as inclinacoes das referencias as subarvores
    
    private No raiz;
    private boolean propSBB;
    private int comparacoes = 0; // Numero de comparacoes feita na pesquisa
    
    private void central (No p) {
      if (p != null) {
        central (p.esq);
        System.out.print ("No:" + p.reg.toString());
        System.out.print (" -- IncE:" + p.incE);
        System.out.println (" -- IncD:" + p.incD);
        central (p.dir);
      }
    }
    private Item pesquisa (Item reg, No p) {
        if (p == null) 
            return null; // Registro nao encontrado
      
        else if (reg.compara (p.reg) < 0) {
            comparacoes++;
            return pesquisa (reg, p.esq);
        }
        else if (reg.compara (p.reg) > 0) {
            comparacoes++;
            return pesquisa (reg, p.dir);
        }    
        else 
            return p.reg;
    }
    
    // Metodos para manutencao da propriedade SBB
    private No ee (No ap) {
      No ap1 = ap.esq; ap.esq = ap1.dir; ap1.dir = ap;
      ap1.incE = Vertical; ap.incE = Vertical; ap = ap1;
      return ap; 
    }
    
    private No ed (No ap) {
      No ap1 = ap.esq; No ap2 = ap1.dir; ap1.incD = Vertical;
      ap.incE = Vertical; ap1.dir = ap2.esq; ap2.esq = ap1;
      ap.esq = ap2.dir; ap2.dir = ap; ap = ap2;    
      return ap; 
    }
  
    private No dd (No ap) {
      No ap1 = ap.dir; ap.dir = ap1.esq; ap1.esq = ap;
      ap1.incD = Vertical; ap.incD = Vertical; ap = ap1;
      return ap; 
    }
  
    private No de (No ap) {
      No ap1 = ap.dir; No ap2 = ap1.esq; ap1.incE = Vertical;
      ap.incD = Vertical; ap1.esq = ap2.dir; ap2.dir = ap1;
      ap.dir = ap2.esq; ap2.esq = ap; ap = ap2;    
      return ap; 
    }
    
    // Metodo para inserir na arvore
    private No insere (Item reg, No pai, No filho, boolean filhoEsq) {
        if (filho == null) {
            filho = new No(); 
            filho.reg = reg; 
            
            filho.incE = Vertical; 
            filho.incD = Vertical;
            
            filho.esq = null; filho.dir = null;
        
            if (pai != null)
                if (filhoEsq) 
                    pai.incE = Horizontal;     
                else 
                    pai.incD = Horizontal;
                this.propSBB = false;
        }
        else if (reg.compara (filho.reg) < 0) {
            filho.esq = insere (reg, filho, filho.esq, true);
            if (!this.propSBB) 
                if (filho.incE == Horizontal) { 
                    if (filho.esq.incE == Horizontal) {
                        filho = this.ee (filho); // trasformacao esquerda-esquerda
                        if (pai != null)
                            if (filhoEsq) 
                                pai.incE=Horizontal;
                            else 
                                pai.incD=Horizontal;
                    }
                    else if (filho.esq.incD == Horizontal) {
                        filho = this.ed (filho); //trasformacao esquerda-direita
                        if (pai != null) 
                            if (filhoEsq) 
                                pai.incE=Horizontal;
                            else 
                                pai.incD=Horizontal;            
                    }
                }
            else 
                this.propSBB = true;
        }
        else if (reg.compara (filho.reg) > 0) {
            filho.dir = insere (reg, filho, filho.dir, false);
            if (!this.propSBB) 
                if (filho.incD == Horizontal) {
                    if (filho.dir.incD == Horizontal) {
                        filho = this.dd (filho); // trasformacao direita-direita
                        if (pai != null)
                            if (filhoEsq) 
                                pai.incE=Horizontal;
                            else
                                pai.incD=Horizontal;
                    }
                    else if (filho.dir.incE == Horizontal) {
                        filho = this.de (filho); // trasformacao direita-esquerda
                        if (pai != null)
                            if (filhoEsq) 
                                pai.incE=Horizontal;
                            else
                                pai.incD=Horizontal;            
                    }
                }
            else 
                this.propSBB = true;
        }
        else {      
            //System.out.println ("Erro: Registro ja existente"); 
            this.propSBB = true;
        }
        return filho;
    }

    public ArvoreSBB () {
        this.raiz = null;
        this.propSBB = true;
    }
      
    public int pesquisa (Item reg) {
        this.pesquisa (reg, this.raiz);
        return this.comparacoes; // Retorna o numero de comparacoes
    }
    
    public void insere (Item reg) {
        this.raiz = insere (reg, null, this.raiz, true);
    }
      
    public void imprime () {
        this.central (this.raiz);
    }

    // Campos e metodos para testar o funcionamento da classe
    private boolean primeiraFolha;
    private int nivelFolhas;
    
    private void testa1 (No p, int nivel) { 
        if (p == null) 
            return;
        if (this.primeiraFolha)
            if (this.nivelFolhas < nivel)  this.nivelFolhas = nivel;
        if (p.esq == null && p.dir == null) { 
            if (this.primeiraFolha)
                this.primeiraFolha = false;
            else { 
                if (nivel != this.nivelFolhas) {
                    System.out.println ("Erro: Folhas em niveis diferentes");
                    System.exit(1);
                }
            }
        }
        if (p.incE == Horizontal) this.testa1(p.esq, nivel);
        else this.testa1(p.esq, nivel + 1);
        
        if (p.incD == Horizontal) this.testa1(p.dir, nivel);
        else this.testa1(p.dir, nivel + 1);
    }  
  
    private void testa2 (No p) { 
        if (p == null)
            return;
        if (p.esq != null) { 
            if (p.reg.compara (p.esq.reg) < 0) { 
                System.out.println ("Erro: Pai " + p.reg.toString() + " menor que filho a esquerda " + p.esq.reg.toString());
            System.exit(1);
            }
        }
        if (p.dir != null) { 
            if (p.reg.compara (p.dir.reg) > 0 ) { 
                System.out.println ("Erro: Pai " + p.reg.toString() + " maior que filho a direita " + p.dir.reg.toString());
                System.exit(1);
            }
        }
        testa2(p.esq);
        testa2(p.dir);
    }

    private void testa(No Arvore) { 
        this.nivelFolhas = 0;
        this.primeiraFolha = true;
        this.testa1(Arvore, 1);
        this.testa2(Arvore);
    }

    public void testa () { 
        this.testa (this.raiz);
    }    
}    