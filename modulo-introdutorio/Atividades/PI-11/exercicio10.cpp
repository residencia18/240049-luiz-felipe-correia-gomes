#include <iostream>
#include <vector>

void encontra_posicoes(const std::string &str, char letra, std::vector<int> &posicoes, int &tamanho)
{
    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] == letra)
        {
            posicoes.push_back(i);
        }
    }

    tamanho = posicoes.size();
}

int main()
{
    std::string texto;
    char letra;

    std::cout << "Digite uma string de caracteres: ";
    std::cin >> texto;

    std::cout << "Digite uma letra: ";
    std::cin >> letra;

    std::vector<int> posicoes;
    int tamanho = 0;

    encontra_posicoes(texto, letra, posicoes, tamanho);

    if (tamanho > 0)
    {
        std::cout << "A letra '" << letra << "' foi encontrada nas posições: ";
        for (int i = 0; i < tamanho; i++)
        {
            std::cout << posicoes[i] << " ";
        }
        std::cout << std::endl;
    }
    else
    {
        std::cout << "A letra '" << letra << "' não foi encontrada na string." << std::endl;
    }

    return 0;
}