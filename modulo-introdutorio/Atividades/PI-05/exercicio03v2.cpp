#include <iostream>

int main()
{
    int num;
    std::cout << "Digite um número inteiro: ";
    std::cin >> num;

    std::cout << "O valor fornecido é" << num << ((num > SHRT_MAX || num < SHRT_MIN) ? " maior que um short int" : " este valor pertence ao intervalo dos short int") << std::endl;

    return 0;
}