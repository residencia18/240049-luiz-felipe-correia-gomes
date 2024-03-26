#include <iostream>

int main(void)
{

    // Programa em C++ que imprime na tela os números de 1 a 100.
    // Para os múltiplos de 3, o programa imprime "Fizz", e para múltiplos de 5, imprime "Buzz".
    // Para números que são múltiplos de ambos, imprime "FizzBuzz".

    for (int i = 1; i < 101; i++)
    {
        std::cout << i << " ";
        if (i % 3 == 0)
        {
            // !(i % 3)
            std::cout << "Fizz";
        }

        if (i % 5 == 0)
        {
            std::cout << "Buzz";
        }

        std::cout << std::endl;
    }
}