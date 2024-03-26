#include <iostream>
#include <stdlib.h>     /* srand, rand */

using namespace std;

int main (void) {

    int quant_estacoes = 250;
    double tmin = 10.0;
    double tmax = 40.0;

    /* random seed*/
    srand(time(NULL));

    double temperaturas[quant_estacoes];
    // temperaturas minimas e maximas das estações
    double temp_max = tmin;
    double temp_min = tmax;
    int max, min;

    for(int i = 0; i < quant_estacoes; i++) {
        temperaturas[i] =  tmin + (1.0 * rand() / RAND_MAX) * (tmax - 10.0);  // numero aleatorio entre tmin e tmax

        cout << temperaturas[i] << endl;
    }

    for(int i = 0; i < quant_estacoes; i++) {
        if (temperaturas[i] >= temp_max) {
            temp_max = temperaturas[i];
            max = i;
        }
        if (temperaturas[i] <= temp_min) {
            temp_min = temperaturas[i];
            min = i;
        }
    }

    cout << endl;


    cout << "Temperatura max: " << temperaturas[max] << endl;
    cout << "Temperatura min: " << temperaturas[min] << endl;

    cout << endl;

    // temperatura media
    double soma_temperaturas = 0;
    for(int i = 0; i < quant_estacoes; i++) {
        soma_temperaturas += temperaturas[i];
    }
    double media_temperaturas = (soma_temperaturas / quant_estacoes);

    cout << "Temperatura media: " << media_temperaturas << endl;

    cout << endl;

    // modelo de previsao  
    for(int i = 0; i < quant_estacoes; i++) {
        if (temperaturas[i] < media_temperaturas) {
            temperaturas[i] -= 2;
        }
        if (temperaturas[i] > media_temperaturas) {
            temperaturas[i] += 1;
        }
    }
  
    cout << "Previsão de tempo: " << endl;  
    for(int i = 0; i < quant_estacoes; i++) {

        cout << temperaturas[i] << endl;
    }


}