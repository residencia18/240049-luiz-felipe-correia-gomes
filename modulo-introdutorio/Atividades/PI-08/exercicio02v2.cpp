#include <iostream>
#include <cstdlib>
#include <ctime>

const int NUM_ESTACOES = 250;
const int TEMPERATURA_MINIMA = 10;
const int TEMPERATURA_MAXIMA = 40;

int main()
{
    // Inicializa o gerador de números aleatórios
    std::srand(std::time(nullptr));

    // Armazena as temperaturas reportadas por cada estação meteorológica
    int temperaturas[NUM_ESTACOES];

    // Gera as temperaturas aleatoriamente entre 10 e 40
    for (int i = 0; i < NUM_ESTACOES; i++)
    {
        temperaturas[i] = std::rand() % (TEMPERATURA_MAXIMA - TEMPERATURA_MINIMA + 1) + TEMPERATURA_MINIMA;
    }

    // Determina a temperatura máxima e mínima
    int temperaturaMaxima = temperaturas[0];
    int temperaturaMinima = temperaturas[0];
    for (int i = 1; i < NUM_ESTACOES; i++)
    {
        if (temperaturas[i] > temperaturaMaxima)
        {
            temperaturaMaxima = temperaturas[i];
        }
        if (temperaturas[i] < temperaturaMinima)
        {
            temperaturaMinima = temperaturas[i];
        }
    }

    // Determina a temperatura média
    int somaTemperaturas = 0;
    for (int i = 0; i < NUM_ESTACOES; i++)
    {
        somaTemperaturas += temperaturas[i];
    }
    double temperaturaMedia = static_cast<double>(somaTemperaturas) / NUM_ESTACOES;

    // Atualiza as temperaturas de acordo com a previsão do modelo
    for (int i = 0; i < NUM_ESTACOES; i++)
    {
        if (temperaturas[i] > temperaturaMedia)
        {
            temperaturas[i] += 1;
        }
        else if (temperaturas[i] < temperaturaMedia)
        {
            temperaturas[i] -= 2;
        }
    }

    // Imprime os resultados
    std::cout << "Temperatura máxima: " << temperaturaMaxima << std::endl;
    std::cout << "Temperatura mínima: " << temperaturaMinima << std::endl;
    std::cout << "Temperatura média: " << temperaturaMedia << std::endl;

    return 0;
}