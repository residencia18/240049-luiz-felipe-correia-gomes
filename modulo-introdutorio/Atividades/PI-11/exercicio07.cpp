#include <iostream>

void multiplica_por_n(int *vet, int qtde, int n)
{
    for (int i = 0; i < qtde; i++)
    {
        vet[i] *= n;
    }
}

int main()
{
    int qtde, multiplicador;

    std::cout << "Digite a quantidade de elementos no vetor: ";
    std::cin >> qtde;

    int *vetor = new int[qtde];

    std::cout << "Digite os elementos do vetor:" << std::endl;
    for (int i = 0; i < qtde; i++)
    {
        std::cout << "Elemento " << i + 1 << ": ";
        std::cin >> vetor[i];
    }

    std::cout << "Digite o multiplicador: ";
    std::cin >> multiplicador;

    multiplica_por_n(vetor, qtde, multiplicador);

    std::cout << "Vetor após a multiplicação por " << multiplicador << ":" << std::endl;
    for (int i = 0; i < qtde; i++)
    {
        std::cout << vetor[i] << " ";
    }
    std::cout << std::endl;

    // Liberar a memória alocada para o vetor
    delete[] vetor;

    return 0;
}