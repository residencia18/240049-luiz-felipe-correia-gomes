#include <iostream>
#include <stdlib.h>     /* srand, rand */


int main (void) {
    
    int numSecreto, numChute, tentativa = 0;

    /* random seed*/
    srand(time(NULL));

    numSecreto = rand() % 100 + 1; // numero aleatorio entre 1 a 100

    do {
        std:: cout << "Chute um numero entre 1 a 100: ";
        std:: cin >> numChute;

        if(numChute < numSecreto){
            std:: cout << "O numero secreto é maior" << std:: endl;
        }

        if(numChute > numSecreto){
            std:: cout << "O numero secreto é menor" << std:: endl;
        }

        tentativa++;
    } while (numChute != numSecreto);

        std:: cout << "Parabéns" << std:: endl;

    return 1;

}