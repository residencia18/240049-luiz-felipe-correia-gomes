package poligonofacade.entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Poligono {
	ArrayList<Ponto> pontos;
	
	public Poligono() {
		this.pontos = new ArrayList<Ponto>();
	}
	
	public static Poligono criaPoligono(int quantidadeDePontos, Scanner leituraDoTeclado) throws InputMismatchException {
		Poligono poligono = new Poligono();
		for(int i = 0; i < quantidadeDePontos; i++) {
			Ponto ponto = Ponto.solicitaPonto(leituraDoTeclado);
			poligono.inserePonto(ponto);
		}
		return poligono;
	}
	
	public void inserePonto(Ponto ponto) {
		pontos.add(ponto);
	}
	
	public double perimetro() {
		double per = 0;
		Ponto p1;
		Ponto p2;
		
		for (int i=0;i<pontos.size()-1;i++) {
			p1 = pontos.get(i);
			p2 = pontos.get(i+1);
			
			per += p1.distanciaPara(p2);
		}
	    
	    p1 = pontos.get(pontos.size() - 1); // Último ponto
	    p2 = pontos.get(0); // Primeiro ponto

	    per += p1.distanciaPara(p2);

	    return per;
	}
}
