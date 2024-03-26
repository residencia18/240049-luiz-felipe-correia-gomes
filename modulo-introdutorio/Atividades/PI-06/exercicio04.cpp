#include <iostream>

int main() {
    double x, y, z;

    std::cout << "Digite dois números de ponto flutuante: ";
    std::cin >> x >> y;

    z = x + y;
    std::cout << "Soma: " << z << std::endl;

    z = (x + y) / 2;
    std::cout << "Média: " << z << std::endl;

    z = x * y;
    std::cout << "Produto: " << z << std::endl;

    if (x > y) {
        z = x;
    } else {
        z = y;
    }
    std::cout << "Maior valor: " << z << std::endl;

    if (x < y) {
        z = x;
    } else {
        z = y;
    }
    std::cout << "Menor valor: " << z << std::endl;

    return 0;
}