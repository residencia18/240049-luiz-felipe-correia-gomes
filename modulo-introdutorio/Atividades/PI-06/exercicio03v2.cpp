#include <iostream>

int main() {
    int num1, num2;

    std::cout << "Digite dois números inteiros: ";
    std::cin >> num1 >> num2;

    if (num1 > num2) {
        std::cout << num1 << " é maior" << std::endl;
    } else if (num2 > num1) {
        std::cout << num2 << " é maior" << std::endl;
    } else {
        std::cout << "Estes números são iguais" << std::endl;
    }

    int maior = (num1 > num2) ? num1 : num2;

    if (maior % 2 == 0) {
        std::cout << maior << " é um número par" << std::endl;
    } else {
        std::cout << maior << " é um número ímpar" << std::endl;
    }

    return 0;
}