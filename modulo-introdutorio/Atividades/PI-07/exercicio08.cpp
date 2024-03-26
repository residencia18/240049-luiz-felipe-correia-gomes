#include <iostream>

int main(void)
{
    int num;
    int soma = 0;

    std::cout << "Numero: ";
    std::cin >> num;

    for (int i = 1; i < num; i++)
    {
        if (num % i == 0)
        {
            std:: cout << " +" << i;
            soma += i;
        }
    }
    
    std:: cout << " = " << soma << std:: endl;

    if (num == soma)
    {
        std::cout << num << " eh um numero perfeito.";
    }
    else
    {
        std::cout << num << " nao eh um numero perfeito.";
    }
}