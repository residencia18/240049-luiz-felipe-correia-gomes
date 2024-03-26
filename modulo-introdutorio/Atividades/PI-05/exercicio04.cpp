#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;

int main()
{
    double x, y, z;

    cout << "Digite o valor de x: ";
    cin >> x;

    cout << "Digite o valor de y: ";
    cin >> y;

    double f = 5 * x + 2;

    string lado = (y > f) ? "acima" : (y < f) ? "abaixo"
                                                   : "na";
    cout << "O ponto (" << x << ", " << y << ") está " << lado << " da curva.\n";

    z = sqrt(pow(x, 2) + pow(y, 2));
    cout << "A distância euclidiana do ponto (" << x << ", " << y << ") ao centro de coordenadas é: " << z << "\n";

    z = x * y;
    cout << "O produto de x e y em notação científica é: " << scientific << z << "\n";

    return 0;
}