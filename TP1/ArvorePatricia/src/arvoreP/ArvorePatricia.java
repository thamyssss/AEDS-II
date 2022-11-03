package arvoreP;

public class ArvorePatricia {
	//Numero de bits de um char 
	public static final int BitsChar = 8;
	
	private static abstract class PatNo{
		}
	
	private static class PatNoInt extends PatNo {
		int index; PatNo esq, dir;
	}
	
	private static class PatNoExt extends PatNo {
	    String chave; //Tipo chave depende da aplica��o
	  }
	
	private PatNo raiz;
	private int nbitsChave;
	
	 // Retorna o i-esimo bit da chave k a partir da esquerda
    private int bit (int i, String str) {
        if(i != 0) {
            i--; 
            int chave = (int)(str.charAt((int)(i /BitsChar)));
            for (int j = 1; j <= BitsChar - i % BitsChar; j++) 
                chave = chave/2;
            return chave % 2;
        }
        
        else
            return 0;
    }
    
    //Verifica classe de p
    private boolean eExterno (PatNo p) {    
        Class classe = p.getClass ();
        return classe.getName().equals(PatNoExt.class.getName());
      }
    
    //Cria no interno na arvore
    private PatNo criaNoInt (int in, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt ();
        p.index = in;
        p.esq = esq;
        p.dir = dir;
        return p;
      }
    
    //Cria no externo na arvore
    private PatNo criaNoExt (Item it) {
        PatNoExt p = new PatNoExt ();
        p.chave = it.chave;
        return p;
      }
    
    //Metodo que realiza pesquisa da string em um no
    private void pesquisa(String str, PatNo t) {
        if (!this.eExterno (t)) {
            PatNoInt aux = (PatNoInt)t;
            if (this.bit (aux.index, str) == 0) 
                pesquisa (str, aux.esq);
            else 
                pesquisa (str, aux.dir);
        }
        else {
            PatNoExt aux = (PatNoExt)t;
            // se a string estiver na chave
            if (!aux.chave.equals(str))
                System.out.println ("Elemento nao encontrado");
            else {
                System.out.println ("Elemento encontrado");
            }
        }
    }
	
    //Insere item
    private PatNo insereEntre(Item it, PatNo t, int i) {
        PatNoInt aux = null; 
        if (!this.eExterno (t)) 
        	aux = (PatNoInt)t;
        
        //Cria um novo no externo
        if (this.eExterno (t) || (i < aux.index)) {
            PatNo p = this.criaNoExt (it);
            if (this.bit (i, it.chave) == 1) 
            	return this.criaNoInt (i, t, p);
            else 
            	return this.criaNoInt (i, p, t);
          }
        else {
            if (this.bit (aux.index, it.chave) == 1) 
              aux.dir = this.insereEntre (it, aux.dir, i);
            else 
              aux.esq = this.insereEntre (it, aux.esq, i);
            return aux;
          }
    }
    private PatNo insere (Item it, PatNo t) {
        if (t == null) 
        	return this.criaNoExt (it);
        else {
          PatNo p = t;
          while (!this.eExterno (p)) {
            PatNoInt aux = (PatNoInt)p;
            if (this.bit (aux.index, it.chave) == 1) 
            	p = aux.dir; 
            else 
            	p = aux.esq;
          }
          PatNoExt aux = (PatNoExt)p;
          //Acha o primeiro bit diferente
          int i = 1;
          while ((i <= this.nbitsChave)&&(this.bit (i, it.chave) == this.bit (i, aux.chave))) 
        	  i++;
          if (i > this.nbitsChave) {
            System.out.println ("Erro: chave ja esta na arvore"); 
            return t;
          }
          else return this.insereEntre (it, t, i);
        }
      }
    private void central (PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
          if (!this.eExterno (filho)) {
            PatNoInt aux = (PatNoInt)filho; 
            	central (filho, aux.esq, "ESQ");
            if (pai != null)
            	System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Int: " + aux.index);
            else 
            	System.out.println ("Pai: "+ pai + " " + msg+ " Int: " + aux.index);
            central (filho, aux.dir, "DIR");
          } else {
            PatNoExt aux = (PatNoExt)filho;
            if (pai != null)
              System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Ext: " + aux.chave);
            else 
            	System.out.println ("Pai: "+ pai + " " + msg+ " Ext: " + aux.chave);
          }
        }
      }
    public void imprime () {
        this.central (null, this.raiz, "RAIZ");
      }

      public ArvorePatricia (int nbitsChave) {
        this.raiz = null; 
        this.nbitsChave = nbitsChave; 
      }
      
      public void pesquisa (String k){ 
    	  this.pesquisa (k, this.raiz);
      }
      
      public void insere (Item k) { 
    	  this.raiz = this.insere (k, this.raiz); 
      }
}