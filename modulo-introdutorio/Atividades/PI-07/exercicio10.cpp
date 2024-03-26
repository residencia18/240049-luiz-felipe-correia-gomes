    #include <iostream>
#include <cmath>

int main() {
    int num, originalNum, remainder, n = 0;
    double result = 0.0;

    std::cout << "Digite um valor inteiro: ";
    std::cin >> num;

    originalNum = num;

    // Encontrando o número de dígitos
    for (originalNum = num; originalNum != 0; ++n) {
        originalNum /= 10;
    }

    originalNum = num;

    // Verificando se é um número Armstrong
    while (originalNum != 0) {
        remainder = originalNum % 10;
        result += std::pow(remainder, n);
        originalNum /= 10;
    }

    if (static_cast<int>(result) == num) {
        std::cout << num << " eh um numero Armstrong." << std::endl;
    } else {
        std::cout << num << " nao eh um numero Armstrong." << std::endl;
    }

    return 0;
}