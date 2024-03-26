#include <iostream>
#include <cmath>

int main()
{
    double a, b, c;
    std::cout << "Digite os coeficientes a, b e c do polinômio de segundo grau: ";
    std::cin >> a >> b >> c;

    double discriminante = b * b - 4 * a * c;
    if (discriminante > 0)
    {
        double raiz1 = (-b + std::sqrt(discriminante)) / (2 * a);
        double raiz2 = (-b - std::sqrt(discriminante)) / (2 * a);
        std::cout << "O polinômio tem duas raízes reais: " << raiz1 << " e " << raiz2 << std::endl;
    }
    else if (discriminante == 0)
    {
        double raiz = -b / (2 * a);
        std::cout << "O polinômio tem uma raiz real: " << raiz << std::endl;
    }
    else
    {
        std::cout << "O polinômio não tem raízes reais." << std::endl;
    }

    double x;
    std::cout << "Digite o valor de x: ";
    std::cin >> x;

    double resultado = a * x * x + b * x + c;
    std::cout << "O valor de p(x) é: " << resultado << std::endl;

    return 0;
}