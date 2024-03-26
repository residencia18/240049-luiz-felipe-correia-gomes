#include <iostream>

using namespace std;

int main (void) {

    unsigned short int geneInt;
    unsigned char gene;

    cout << "Informação genética da planta: ";
    cin >> geneInt;
    
    gene = geneInt;
    
    // operação bit a bit que vai verificar se os bits são iguais e retornar 0 ou 1 dependendo do resultado.
    unsigned char geneTeste = 0b1;
    bool gene0 = gene & geneTeste;
    bool gene1 = gene & (geneTeste << 1);
    bool gene2 = gene & (geneTeste << 2);
    bool gene3 = gene & (geneTeste << 3);
    bool gene4 = gene & (geneTeste << 4);
    bool gene5 = gene & (geneTeste << 5);
    bool gene6 = gene & (geneTeste << 6);
    bool gene7 = gene & (geneTeste << 7);
    bool gene8 = gene & (geneTeste << 8);

    cout << "Resultado: ";
    cout << gene8;   
    cout << gene7;    
    cout << gene6;
    cout << gene5;
    cout << gene4;
    cout << gene3;
    cout << gene2;
    cout << gene1;
    cout << gene0;
    cout << endl;

    return 0;
}