package a9;

public class Carta {
	int valor; 
	String naipe;
	
	public Carta(int valor, String  naipe) {
		super();
		this.valor = valor;
		this.naipe = naipe;
	}

	public String imprimir() {
		String extenso = "";
        if(valor==1) {
     	   extenso = "as";
        }else if(valor==11) {
     	   extenso = "valete";
        }else if(valor==12) {
     	   extenso = "dama";
        }else if(valor==13) {
     	   extenso = "rei";
        }else {
     	   extenso=valor+"";
        }
     	extenso += " de " + naipe;
        
        return extenso;
	}
}
