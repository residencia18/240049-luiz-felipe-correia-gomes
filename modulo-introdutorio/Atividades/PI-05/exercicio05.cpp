#include <iostream>

int main()
{
    unsigned char infoGenetica;

    std::cout << "Digite a informacao genetica da planta: ";
    std::cin >> infoGenetica;

    int genesPresentes = 0;
    for (int i = 0; i < 8; i++)
    {
        if (infoGenetica & (1 << i))
        {
            genesPresentes++;
        }
    }

    std::cout << "Quantidade de genes presentes: " << genesPresentes << std::endl;

    int geneDesejado;
    std::cout << "Digite o numero do gene desejado (entre 1 e 8): ";
    std::cin >> geneDesejado;

    bool genePresente = infoGenetica & (1 << (geneDesejado - 1));

    if (genePresente)
    {
        std::cout << "O gene " << geneDesejado << " esta presente nesta planta." << std::endl;
    }
    else
    {
        std::cout << "O gene " << geneDesejado << " nao esta presente nesta planta." << std::endl;
    }

    return 0;
}