#include <iostream>

using namespace std;

int main(void)
{

    int altura;
    int degrau;
    int auxiliar = 0;
    cout << "Altura da escada ABC: ";
    cin >> altura;
    
    char letra = 'A';

    for (int linha = 1; linha <= altura; ++linha)
    {
        for (int i = 1; i <= linha; ++i)
        {
            cout << letra; 
            ++letra; // Avance para a próxima letra
        }
        cout << endl; // Avance para a próxima linha
    }
}