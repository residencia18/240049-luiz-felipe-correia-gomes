#include <iostream>

float calc_serie(int N)
{
    float soma = 0.0;

    for (int numerador = 1, denominador = N; numerador <= N; numerador++, denominador--)
    {
        soma += static_cast<float>(numerador) / static_cast<float>(denominador);
    }

    return soma;
}

int main()
{
    int N;

    std::cout << "Digite um valor inteiro N: ";
    std::cin >> N;

    if (N <= 0)
    {
        std::cerr << "Erro: N deve ser maior que zero." << std::endl;
        return 1;
    }

    float resultado = calc_serie(N);

    std::cout << "A soma da série é: " << resultado << std::endl;

    return 0;
}
