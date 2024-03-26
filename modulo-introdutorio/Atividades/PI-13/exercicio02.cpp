#include <iostream>
#include <string>

// Definição da struct Empregado
struct Empregado
{
    std::string nome;
    std::string sobrenome;
    int anoNascimento;
    std::string RG;
    int anoAdmissao;
    double salario;
};

// Função para reajustar o salário em 10%
void Reajusta_dez_porcento(Empregado empregados[], int numEmpregados)
{
    for (int i = 0; i < numEmpregados; i++)
    {
        empregados[i].salario *= 1.10; // Aumento de 10%
    }
}

int main()
{
    // Criação de um vetor de empregados para armazenar até 50 empregados
    Empregado empregados[50];

    // Exemplo de preenchimento de dados para três empregados
    empregados[0] = {"João", "Silva", 1980, "123456789", 2005, 5000.0};
    empregados[1] = {"Maria", "Santos", 1990, "987654321", 2010, 6000.0};
    empregados[2] = {"Pedro", "Almeida", 1985, "456789123", 2008, 5500.0};

    int numEmpregados = 3;

    // Chamando a função para reajustar o salário em 10%
    Reajusta_dez_porcento(empregados, numEmpregados);

    // Exibindo os dados atualizados dos empregados
    std::cout << "Dados dos empregados após o reajuste de 10%:\n";
    for (int i = 0; i < numEmpregados; i++)
    {
        std::cout << "Nome: " << empregados[i].nome << " " << empregados[i].sobrenome << "\n";
        std::cout << "Ano de Nascimento: " << empregados[i].anoNascimento << "\n";
        std::cout << "RG: " << empregados[i].RG << "\n";
        std::cout << "Ano de Admissão: " << empregados[i].anoAdmissao << "\n";
        std::cout << "Salário: R$" << empregados[i].salario << "\n\n";
    }

    return 0;
}
