#include <iostream>
#include <cstdlib>
#include <ctime>

const int WIDTH = 640;
const int HEIGHT = 480;

int main() {
    // Inicializar o gerador de números aleatórios
    std::srand(std::time(nullptr));

    // Criar a matriz para representar a imagem
    int imagem[WIDTH][HEIGHT];

    // Preencher a matriz com valores aleatórios entre 0 e 255
    for (int y = 0; y < HEIGHT; y++) {
        for (int x = 0; x < WIDTH; x++) {
            imagem[x][y] = std::rand() % 256;
        }
    }

    // Construir o histograma
    int histograma[256] = {0};
    for (int y = 0; y < HEIGHT; y++) {
        for (int x = 0; x < WIDTH; x++) {
            int intensidade = imagem[x][y];
            histograma[intensidade]++;
        }
    }

    // Imprimir o histograma
    for (int i = 0; i < 256; i++) {
        std::cout << i << ": " << histograma[i] << std::endl;
    }

    return 0;
}