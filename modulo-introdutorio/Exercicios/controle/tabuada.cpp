#include <iostream>

int main (void) {

    int num;

    std:: cout << "Número que você quer consultar a tabuada: ";
    std:: cin >> num;

    int multiplicador = 0;
    while (multiplicador <= 10) {
        std:: cout << num << " * " << multiplicador << " = " << num * multiplicador << std::endl; 

        multiplicador++;
    }

}