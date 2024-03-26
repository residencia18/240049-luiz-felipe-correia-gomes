package construtores.aplicacao;

import construtores.entidades.Engenheiro;
import construtores.entidades.Projeto;

public class Aplicacao {
    
    public static void main(String[] args) {
        
        Engenheiro engenheiro = new Engenheiro("João", "123456");
        
        // Testando construtores
        Projeto projeto1 = new Projeto("123456", "Projeto 1", 1000.0, engenheiro);
        Projeto projeto2 = new Projeto("123456", "Projeto 2", 1000.0);
        Projeto projeto3 = new Projeto("123456", "Projeto 3");

        System.out.println(projeto1);
        System.out.println("---");
        System.out.println(projeto2);
        System.out.println("---");
        System.out.println(projeto3);
        System.out.println("---");

    }
}
