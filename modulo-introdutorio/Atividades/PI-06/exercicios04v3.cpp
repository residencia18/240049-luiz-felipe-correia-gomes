#include <iostream>

int main() {
    int x, y;
    
    std::cout << "Digite as coordenadas (x, y) do ponto: ";
    std::cin >> x >> y;
    
    std::string quadrante = (x > 0 && y > 0) ? "primeiro quadrante" :
                            (x < 0 && y > 0) ? "segundo quadrante" :
                            (x < 0 && y < 0) ? "terceiro quadrante" :
                            (x > 0 && y < 0) ? "quarto quadrante" :
                            (x == 0 || y == 0) ? "sobre um dos eixos" :
                            "ponto inválido";
                            
    std::cout << "O ponto está no " << quadrante << std::endl;
    
    return 0;
}