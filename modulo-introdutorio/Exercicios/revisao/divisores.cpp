#include <iostream>

using namespace std;

int main (void) {

    int numInt;

    cout << "Numero: ";
    cin >> numInt;

    cout << "Divisores: " << endl;
    for (int i = numInt; numInt > 0; i--) {
        if (numInt % i == 0)
            cout << i << endl;
    }

    return 0;
}   