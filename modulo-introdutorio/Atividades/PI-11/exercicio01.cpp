#include <iostream>

void maxmin(int vetor[], int n, int &maximo, int &minimo)
{
    if (n <= 0)
    {
        std::cerr << "Erro: O vetor está vazio." << std::endl;
        return;
    }

    maximo = vetor[0];
    minimo = vetor[0];

    for (int i = 1; i < n; i++)
    {
        if (vetor[i] > maximo)
        {
            maximo = vetor[i];
        }
        else if (vetor[i] < minimo)
        {
            minimo = vetor[i];
        }
    }
}

int main()
{
    int tamanho;
    std::cout << "Digite o tamanho do vetor: ";
    std::cin >> tamanho;

    if (tamanho <= 0)
    {
        std::cerr << "Erro: O tamanho do vetor deve ser maior que zero." << std::endl;
        return 1; // Código de erro
    }

    int *vetor = new int[tamanho];

    std::cout << "Digite os elementos do vetor:" << std::endl;
    for (int i = 0; i < tamanho; i++)
    {
        std::cout << "Elemento " << i + 1 << ": ";
        std::cin >> vetor[i];
    }

    int maximo, minimo;
    maxmin(vetor, tamanho, maximo, minimo);

    std::cout << "O valor máximo é: " << maximo << std::endl;
    std::cout << "O valor mínimo é: " << minimo << std::endl;

    delete[] vetor; // Liberar a memória alocada para o vetor

    return 0;
}