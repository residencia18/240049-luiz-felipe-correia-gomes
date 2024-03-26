#include <iostream>
#include <cmath>

int main() {
    double raio;
    std::cout << "Digite o raio do círculo: ";
    std::cin >> raio;

    double diametro = 2 * raio;
    double circunferencia = 2 * M_PI * raio;
    double area = M_PI * pow(raio, 2);

    std::cout << "Diâmetro: " << diametro << std::endl;
    std::cout << "Circunferência: " << circunferencia << std::endl;
    std::cout << "Área: " << area << std::endl;

    return 0;
}