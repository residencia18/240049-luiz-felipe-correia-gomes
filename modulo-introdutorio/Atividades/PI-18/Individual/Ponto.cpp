#include <iostream>
#include <cmath>

class Ponto {
private:
    double x;
    double y;

public:
    // Construtor padrão
    Ponto() : x(0.0), y(0.0) {}

    // Construtor com coordenadas iniciais
    Ponto(double x, double y) : x(x), y(y) {}

    // Métodos para definir as coordenadas
    void setX(double x) {
        this->x = x;
    }

    void setY(double y) {
        this->y = y;
    }

    void setCoordenadas(double x, double y) {
        this->x = x;
        this->y = y;
    }

    // Métodos para obter as coordenadas
    double getX() const {
        return x;
    }

    double getY() const {
        return y;
    }

    // Método para calcular a distância até a origem (0,0)
    double calcularDistancia() const {
        return std::sqrt(x * x + y * y);
    }
};

int main() {
    Ponto p1(3, 4);
    double distancia_p1 = p1.calcularDistancia();
    std::cout << "A distância do ponto (3, 4) até a origem é aproximadamente " << distancia_p1 << ".\n";

    Ponto p2(-2, 7);
    p2.setCoordenadas(1, 1);
    double distancia_p2 = p2.calcularDistancia();
    std::cout << "A distância do ponto (1, 1) até a origem é aproximadamente " << distancia_p2 << ".\n";

    Ponto p3(0, 3);
    Ponto p4(4, 0);
    double distancia_p3 = p3.calcularDistancia();
    double distancia_p4 = p4.calcularDistancia();
    std::cout << "A distância do ponto (0, 3) até a origem é " << distancia_p3 << " e do ponto (4, 0) até a origem é " << distancia_p4 << ".\n";

    Ponto pontos[3];
    pontos[0].setCoordenadas(2, 2);
    pontos[1].setCoordenadas(-1, 5);
    pontos[2].setCoordenadas(0, 0);
    for (int i = 0; i < 3; ++i) {
        double distancia = pontos[i].calcularDistancia();
        std::cout << "Distância do ponto " << i + 1 << " até a origem: " << distancia << "\n";
    }

    Ponto p5;
    std::cout << "Coordenadas do ponto p5: (" << p5.getX() << ", " << p5.getY() << ")\n";
    p5.setCoordenadas(8, -3);
    std::cout << "Novas coordenadas do ponto p5: (" << p5.getX() << ", " << p5.getY() << ")\n";

    return 0;
}
