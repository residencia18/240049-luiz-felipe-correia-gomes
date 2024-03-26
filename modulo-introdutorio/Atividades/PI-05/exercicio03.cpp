#include <iostream>
#include <iomanip>

int main()
{
    int a, b, c;

    std::cout << "Digite dois números inteiros: ";
    std::cin >> a >> b;

    c = a + b;

    std::cout << "Resultado em formato hexadecimal: " << std::hex << c << std::endl;
    std::cout << "Resultado em formato octal: " << std::oct << c << std::endl;

    c = std::abs(a - b);

    std::cout << "Módulo da diferença entre a e b: " << c << std::endl;

    c = (b != 0) ? (a / b) : 0;
    std::cout << "Quociente entre a e b: " << c << std::endl;

    std::cout << ((a % b == 0) ? "a é divisível de forma exata por b." : "a não é divisível de forma exata por b.") << std::endl;

    return 0;
}