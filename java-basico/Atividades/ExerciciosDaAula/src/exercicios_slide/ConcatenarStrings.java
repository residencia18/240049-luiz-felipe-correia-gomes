package exercicios_slide;

import java.util.Scanner;

public class ConcatenarStrings {
	static String solicitarString(Scanner input) {
		System.out.print("Insira a string: ");
		String str = input.nextLine();
		return str;
	}
	
	static void exibirString(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str1 = solicitarString(input);
		String str2 = solicitarString(input);
		
		String stringConcatenada = str1.concat(str2);
		
		exibirString(stringConcatenada);
		
		input.close();
	}

}
