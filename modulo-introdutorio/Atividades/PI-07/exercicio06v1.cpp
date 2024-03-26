#include <iostream>
#include <iomanip>

int main()
{
    int matricula;
    double nota1, nota2, nota3;
    char continuar;

    do
    {
        // Solicita a matrícula e as notas do aluno
        std::cout << "===================================" << std::endl;
        std::cout << "Digite a matricula do aluno (apenas numeros): ";
        std::cin >> matricula;

        std::cout << "Digite a primeira nota: ";
        std::cin >> nota1;

        std::cout << "Digite a segunda nota: ";
        std::cin >> nota2;

        std::cout << "Digite a terceira nota: ";
        std::cin >> nota3;
        std::cout << "===================================" << std::endl;

        // Calcula a média das notas
        double media = (nota1 + nota2 + nota3) / 3.0;

        std::cout << "MATRICULA  NOTA1  NOTA2  NOTA3  MEDIA" << std::endl;
        std::cout << "===================================" << std::endl;

        // Imprime os dados do aluno em formato tabular
        std::cout << std::setw(9) << matricula << "  "
                  << std::setw(5) << nota1 << "  "
                  << std::setw(5) << nota2 << "  "
                  << std::setw(5) << nota3 << "  "
                  << std::setw(5) << media << std::endl;
        std::cout << "===================================" << std::endl;

        // Pergunta se deseja continuar cadastrando alunos
        std::cout << "Deseja cadastrar outro aluno? (S/N): ";
        std::cin >> continuar;
        std::cout << "===================================" << std::endl;

    } while (continuar == 'S' || continuar == 's');

    return 0;
}