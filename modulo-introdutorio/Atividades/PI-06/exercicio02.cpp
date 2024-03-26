#include <iostream>

int main()
{
    int a, b, c;

    std::cout << "Digite dois números inteiros: ";
    std::cin >> a >> b;

    c = 4 * a + b / 3 - 5;
    std::cout << "Resultado da expressão 4 * a + b / 3 - 5: " << c << std::endl;

    c = 4 * (a + b) / (3 - 5);
    std::cout << "Resultado da expressão 4 * (a + b) / (3 - 5): " << c << std::endl;

    // Os resultados das expressões são diferentes porque a ordem das operações é diferente.
    // Na primeira expressão, a multiplicação e a divisão são executadas antes da soma e subtração.
    // Na segunda expressão, a soma e a subtração são executadas antes da multiplicação e divisão.

    c = a;
    std::cout << "Resultado da expressão a: " << c << std::endl;

    return 0;
}