package arvoreP;

import java.util.StringTokenizer;
import java.io.*;

public class ExtraiPalavra {
	public final int linhaMatriz = 0;
    public final int colunaMatriz = 0;
    private BufferedReader arqDelim, arqTxt;
	private StringTokenizer palavras;
	private String delimitadores;
	
	public ExtraiPalavra (String nomeArqDelim, String nomeArqTxt) 
	throws Exception {
		this.arqDelim = new BufferedReader (new FileReader (nomeArqDelim));
        this.arqTxt = new BufferedReader (new FileReader (nomeArqTxt));
        
        //Os delimitadores estao juntos em uma unica linha do arquivo chamado delimitador.txt
        this.delimitadores = arqDelim.readLine() + '\r' + '\n';
        this.palavras = null;

	}
	
	public String proxPalavra() throws Exception{
		if (palavras == null || !palavras.hasMoreTokens()) {
		      String linha = arqTxt.readLine();
		      
		      if (linha == null)
		    	  return null;
		      
		      this.palavras = new StringTokenizer (linha, this.delimitadores);
		      
		      if (!palavras.hasMoreTokens()) 
		    	  return ""; //Ignora delimitadores
		    }
		return this.palavras.nextToken ();
	}
	
	public void fecharArquivos() throws Exception {
	    this.arqDelim.close(); 
	    this.arqTxt.close();
	  }

	public String nextWord() {
		return null;
	}
}
