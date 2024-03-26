#include <iostream>
#include <stdlib.h>     /* srand, rand */
#include <ctime>

int main (void) {
    
    int numSecreto, numChute, tentativa = 0;

    /* random seed*/
    srand(time(NULL));

    numSecreto = rand() % 100 + 1; // numero aleatorio entre 1 a 100

    do {
        std:: cout << "Chute um numero entre 1 a 100: ";
        std:: cin >> numChute;

        if(numChute < numSecreto){
            std:: cout << "O numero secreto eh maior" << std:: endl;
        }

        if(numChute > numSecreto){
            std:: cout << "O numero secreto eh menor" << std:: endl;
        }

        tentativa++;
    } while (numChute != numSecreto);

        std:: cout << "Ganhou na "<< tentativa << " tentativa!" << std:: endl;

    return 1;

}