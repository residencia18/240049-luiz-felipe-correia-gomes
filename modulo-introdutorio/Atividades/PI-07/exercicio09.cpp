#include <iostream>

int main() {
    int n, primeiro = 0, segundo = 1, proximo;

    std::cout << "Digite um numero inteiro positivo: ";
    std::cin >> n;

    std::cout << "Sequencia de Fibonacci ate " << n << ":\n";

    if (n >= 1) {
        std::cout << primeiro << " ";

        if (n >= 2) {
            std::cout << segundo << " ";

            for (int i = 2; i < n; ++i) {
                proximo = primeiro + segundo;
                std::cout << proximo << " ";
                primeiro = segundo;
                segundo = proximo;
            }
        }
    }

    std::cout << "\n";

    return 0;
}