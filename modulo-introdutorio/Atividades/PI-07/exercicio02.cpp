#include <iostream>

using namespace std;

// Programa que lê um número inteiro e verifique se ele é um palíndromo.
// Um palíndromo é um número que permanece o mesmo quando seus dígitos são invertidos.

int main(void)
{

    int numeroInteiro, numeroInvertido = 0, cifra;
    cout << "Digite um inteiro: ";
    cin >> numeroInteiro;
    
    int numero = numeroInteiro; // Copiando o valor que vai ser usado nas operacoes de verificacao

    if (numero >= 0)
    {
        do
        {
            cifra = numero % 10;
            numeroInvertido = numeroInvertido * 10 + cifra;
            numero /= 10;
        } while (numero != 0);
    }
    else
    {
        numero *= -1; // transformar o numero negativo em positivo
        do
        {
            cifra = numero % 10;
            numeroInvertido = numeroInvertido * 10 + cifra;
            numero /= 10;
        } while (numero != 0);

        numeroInvertido *= -1; // o numero volta a ser negativo
    }

    if (numeroInteiro == numeroInvertido)
    {
        cout << numeroInteiro << " eh um palindromo.";
    }
    else
    {
        cout << numeroInteiro << " nao eh um palindro. Ele invertido fica " << numeroInvertido;
    }
}