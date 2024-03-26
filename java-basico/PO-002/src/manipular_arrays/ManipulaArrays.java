package manipular_arrays;

import java.util.Scanner;
import java.util.Random;

public class ManipulaArrays {
    
	public static int[] readArrayFromUser() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Informe o tamanho do array: ");
        int size = input.nextInt();

        int[] arrayUser = new int[size];

        System.out.println("Informe os elementos do array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            arrayUser[i] = input.nextInt();
        }

        input.close();
        return arrayUser;
    }
	
    public static int[] createRandomArray(int size, int minValue, int maxValue) {
        int[] arrayRandom = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arrayRandom[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        return arrayRandom;
    }
    
    public static void main(String[] args) {
        int[] arrayRandom = createRandomArray(5, 1, 10); // Exemplo com array de tamanho 5, valores entre 1 e 10
        int[] arrayUser = readArrayFromUser();
       
        for (int number : arrayRandom) {
            System.out.print(number + " ");
        }
        
        System.out.println();
        
        for (int number : arrayUser) {
        	System.out.print(number + " ");
        }  
    }
    
	

	
}
