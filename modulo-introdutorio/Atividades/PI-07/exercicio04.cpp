#include <iostream>

using namespace std;

// Programa que imprime todos os numeros primos de 1 a 100

// Teste de Primalidade de Fermat aprimorado
bool isPrimo(int numero)
{
    if (numero <= 1)
    {
        return false;
    }
    if (numero <= 3)
    {
        return true;
    }
    if (numero % 2 == 0 || numero % 3 == 0)
    {
        return false;
    }

    for (int i = 5; i * i <= numero; i += 6)
    {
        if (numero % i == 0 || numero % (i + 2) == 0)
        {
            return false;
        }
    }

    return true;
}

int main(void)
{
    int N = 100;
    cout << "Primos de 1 a " << N << ": ";
    for (int i = 1; i < N; i++)
    {
        if(isPrimo(i))
        {
            cout << i << "  ";
        }

    }

    return 0;
}