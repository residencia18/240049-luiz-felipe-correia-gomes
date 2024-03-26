#include <iostream>

void calcula(int &X, int &Y)
{
    int soma = X + Y;
    int subtracao = X - Y;

    X = soma;
    Y = subtracao;
}

int main()
{
    int num1, num2;

    std::cout << "Digite o o primeiro número inteiro:" << std::endl;
    std::cin >> num1; 

    std::cout << "Digite o segundo número inteiro:" << std::endl;
    std::cin >> num2;

    calcula(num1, num2);

    std::cout << "Após a chamada da função, X agora é: " << num1 << std::endl;
    std::cout << "Após a chamada da função, Y agora é: " << num2 << std::endl;

    return 0;
}
