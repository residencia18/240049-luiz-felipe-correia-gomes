package filemanager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {

    private String name;
    private String cpf;
    private double cra;
    private int yearOfAdmission;

    public Student(String name, String cpf, double cra, int yearOfAdmission) {
        this.name = name;
        this.cpf = cpf;
        this.cra = cra;
        this.yearOfAdmission = yearOfAdmission;
    }

    public static Student create(Scanner scanner) {
        System.out.print("Nome: ");
        String name = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        try {
            System.out.print("CRA: ");
            double cra = scanner.nextDouble();
            System.out.print("Ano de admissão: ");
            int yearOfAdmission = scanner.nextInt();
            Student estudante = new Student(name, cpf, cra, yearOfAdmission);
            return estudante;

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Tente novamente.");
            scanner.nextLine();
            return null;
        }

    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getCra() {
        return cra;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nCPF: " + cpf + "\nCRA: " + cra + "\nAno de admissão: " + yearOfAdmission;
    }
}
