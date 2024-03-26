#include <iostream>

using namespace std;

int main (void) {

    float x, y;

    cout << "x: ";
    cin >> x;

    cout << "y: ";
    cin >> y;
    
    cout << "SOMA (x + y): " << x + y << endl;
    cout << "SUB (x - y): " << x - y << endl;
    cout << "MULT (x * y): " << x * y << endl;
    cout << "DIV (x / y): " << x / y << endl;
    
    return 0;
}