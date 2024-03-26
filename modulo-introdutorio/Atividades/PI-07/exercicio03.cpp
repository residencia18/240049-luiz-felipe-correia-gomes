#include <iostream>

using namespace std;

// Programa que lê um número inteiro e imprime todos seus divisores

int main(void)
{

    int numInt;

    cout << "Numero: ";
    cin >> numInt;

    cout << "Divisores: ";
    for (int i = numInt; numInt > 0; i--)
    {
        if (numInt % i == 0)
        {
            cout << i << "  ";
        }
    }

    return 0;
}