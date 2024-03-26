#include <iostream>

using namespace std;

int soma(int a, int b);
int subtracao(int a, int b);
double divisao(double a, double b);
int multiplicacao(int a, int b);
int restoDivisao(int a, int b);

int main()
{
    int a, b;
    cout << "Valor de a: ";
    cin >> a;
    cout << "Valor de b: ";
    cin >> b;

    // Menu para escolher a operação desejada
    int op;
    cout << "1 - Soma" << endl;
    cout << "2 - Subtracao" << endl;
    cout << "3 - Divisao" << endl;
    cout << "4 - Multiplicacao" << endl;
    cout << "5 - Resto da divisao" << endl;
    cin >> op;
    switch (op)
    {
    case 1:
        cout << "Soma: " << soma(a, b) << endl;
        break;
    case 2:
        cout << "Subtracao: " << subtracao(a, b) << endl;
        break;
    case 3:
        cout << "Divisao: " << divisao(a, b) << endl;
        break;
    case 4:
        cout << "Multiplicacao: " << multiplicacao(a, b) << endl;
        break;
    case 5:
        cout << "Resto da divisao: " << restoDivisao(a, b) << endl;
        break;
    default:
        cout << "Opcao invalida" << endl;
        break;
    }
}

int soma(int a, int b)
{
    return a + b;
}