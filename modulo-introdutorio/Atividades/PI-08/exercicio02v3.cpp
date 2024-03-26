#include <iostream>
#include <cstdlib>
#include <ctime>
#include <vector>

float gerarNotaAleatoria()
{
    return ((float)rand() / RAND_MAX) * 10;
}

void imprimirDesempenho(float nota1, float nota2)
{
    if (nota2 > nota1)
    {
        std::cout << "Melhorou" << std::endl;
    }
    else if (nota2 < nota1)
    {
        std::cout << "Piorou" << std::endl;
    }
    else
    {
        std::cout << "Manteve a nota" << std::endl;
    }
}

float calcularMedia(float nota1, float nota2)
{
    return (nota1 + nota2) / 2;
}

int main()
{
    srand(time(NULL));

    const int NUM_ALUNOS = 15;
    std::vector<float> notas1(NUM_ALUNOS);
    std::vector<float> notas2(NUM_ALUNOS);
    std::vector<float> medias(NUM_ALUNOS);

    // Simular notas da primeira avaliação
    for (int i = 0; i < NUM_ALUNOS; i++)
    {
        notas1[i] = gerarNotaAleatoria();
    }

    // Simular notas da segunda avaliação e imprimir desempenho
    std::cout << "Desempenho dos alunos:" << std::endl;
    for (int i = 0; i < NUM_ALUNOS; i++)
    {
        notas2[i] = gerarNotaAleatoria();
        std::cout << "Aluno " << i + 1 << ": ";
        imprimirDesempenho(notas1[i], notas2[i]);
    }

    // Calcular médias
    for (int i = 0; i < NUM_ALUNOS; i++)
    {
        medias[i] = calcularMedia(notas1[i], notas2[i]);
    }

    // Imprimir médias
    std::cout << "\nMédias dos alunos:" << std::endl;
    for (int i = 0; i < NUM_ALUNOS; i++)
    {
        std::cout << "Aluno " << i + 1 << ": " << medias[i] << std::endl;
    }

    return 0;
}