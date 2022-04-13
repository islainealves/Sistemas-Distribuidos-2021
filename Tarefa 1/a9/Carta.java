package a9;

public class Carta {
	int valor, naipe;
	
	public Carta(int valor, int naipe) {
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
        if(naipe==1) {
     	   extenso += " de ouros";
        }else if(naipe==2) {
     	   extenso += " de paus";
        }else if(naipe==3) {
     	   extenso += " de copas";
        }else if(naipe==4) {
     	   extenso += " de espadas";
        }
        return extenso;
	}
}
