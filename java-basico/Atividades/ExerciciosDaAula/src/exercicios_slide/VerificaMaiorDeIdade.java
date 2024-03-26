package exercicios_slide;

import java.util.Calendar;
import java.util.Scanner;

public class VerificaMaiorDeIdade {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Versão pedindo a idade
        System.out.print("Sua idade: ");
        int idade = input.nextInt();

        if (idade >= 18) {
            System.out.println("Maior de idade");
        } else {
            System.out.println("Menor de idade");
        }

        // Versão pedindo a data de nascimento
        System.out.print("Dia de nascimento: ");
        int dia = input.nextInt();
        System.out.print("Mês de nascimento: ");
        int mes = input.nextInt();
        System.out.print("Ano de nascimento: ");
        int ano = input.nextInt();

        // Calcula a idade com base na data de nascimento usando Calendar
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(ano, mes - 1, dia); // Mês começa do zero no Calendar (janeiro = 0)
        Calendar dataAtual = Calendar.getInstance();
        
        int idadeCalculada;
        if (dataAtual.get(Calendar.MONTH) >= dataNascimento.get(Calendar.MONTH) && dataAtual.get(Calendar.DAY_OF_MONTH) >= dataNascimento.get(Calendar.DAY_OF_MONTH))
        	idadeCalculada = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        else 
        	idadeCalculada = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR) - 1;
        
        System.out.println("Sua idade: " + idadeCalculada);

        // Verifica se é maior ou menor de idade com base na idade calculada
        if (idadeCalculada >= 18) {
            System.out.println("Maior de idade");
        } else {
            System.out.println("Menor de idade");
        }

        input.close();
    }
}
