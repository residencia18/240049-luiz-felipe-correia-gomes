#include <iostream>

using namespace std;

int main(void)
{
    int altura;
    cout << "Altura dos padroes: ";
    cin >> altura;

    // QUADRADO
    cout << "Quadrado: " << endl;
    for (int linha = 0; linha < altura; linha++)
    {
        for (int i = 0; i < altura; i++)
        {
            cout << " * ";
        }

        cout << endl;
    }

    // ESCADA
    cout << "Escada: " << endl;
    for (int linha = 0; linha < altura; linha++)
    {
        for (int i = 0; i <= linha; i++)
        {
            cout << " * ";
        }
        cout << endl;
    }

    // CANTOS
    cout << "Cantos: " << endl;
    for (int linha = 0; linha < altura; linha++)
    {
        for (int coluna = 0; coluna < altura; coluna++)
        {
            if (coluna == 0 || coluna == altura - 1 || linha == 0 || linha == altura - 1)
            {
                cout << " * ";
            }
            else
            {
                cout << "   ";
            }
        }
        cout << endl;
    }

    cout << "Padroes impressos com sucesso!" << endl;
}