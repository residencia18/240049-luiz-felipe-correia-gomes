#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <algorithm>

char gerarCaractereAleatorio() {
    return 'a' + std::rand() % ('z' - 'a' + 1);
}

std::string gerarStringAleatoria() {
    std::string str;
    for (int i = 0; i < 10; i++) {
        str += gerarCaractereAleatorio();
    }
    return str;
}

void transformarPrimeiroCaractereMaiuscula(std::string& str) {
    if (!str.empty()) {
        str[0] = std::toupper(str[0]);
    }
}

int main() {
    // Inicializar o gerador de números aleatórios
    std::srand(std::time(nullptr));

    // Gerar as duas strings aleatórias
    std::string str1 = gerarStringAleatoria();
    std::string str2 = gerarStringAleatoria();

    // Transformar o primeiro caractere de cada string em maiúscula
    transformarPrimeiroCaractereMaiuscula(str1);
    transformarPrimeiroCaractereMaiuscula(str2);

    // Imprimir as strings em ordem alfabética
    if (str1 < str2) {
        std::cout << str1 << std::endl;
        std::cout << str2 << std::endl;
    } else {
        std::cout << str2 << std::endl;
        std::cout << str1 << std::endl;
    }

    return 0;
}