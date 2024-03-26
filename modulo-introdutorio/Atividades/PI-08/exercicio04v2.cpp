#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <algorithm>
#include <vector>

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

    // Gerar a lista de 10 strings aleatórias
    std::vector<std::string> strings;
    for (int i = 0; i < 10; i++) {
        std::string str = gerarStringAleatoria();
        transformarPrimeiroCaractereMaiuscula(str);
        strings.push_back(str);
    }

    // Ordenar as strings em ordem alfabética
    std::sort(strings.begin(), strings.end());

    // Imprimir as strings
    for (const std::string& str : strings) {
        std::cout << str << std::endl;
    }

    return 0;
}