#include <iostream>
#include <string>

// Função para codificar uma string
std::string codificar(const std::string &input)
{
    std::string codigo = input; // Inicializa a string de código com a entrada

    for (char &c : codigo)
    {
        if (std::isalpha(c))
        { // Verifica se o caractere é uma letra
            if (c == 'Z')
            {
                c = 'A'; // Substitui 'Z' por 'A'
            }
            else if (c == 'z')
            {
                c = 'a'; // Substitui 'z' por 'a'
            }
            else
            {
                c++; // Substitui a letra pela letra seguinte
            }
        }
    }

    return codigo;
}

// Função para decodificar uma string
std::string decodificar(const std::string &input)
{
    std::string decodificado = input; // Inicializa a string decodificada com a entrada

    for (char &c : decodificado)
    {
        if (std::isalpha(c))
        { // Verifica se o caractere é uma letra
            if (c == 'A')
            {
                c = 'Z'; // Substitui 'A' por 'Z'
            }
            else if (c == 'a')
            {
                c = 'z'; // Substitui 'a' por 'z'
            }
            else
            {
                c--; // Substitui a letra pela letra anterior
            }
        }
    }

    return decodificado;
}

int main()
{
    std::string texto;

    std::cout << "Digite uma string: ";
    std::getline(std::cin, texto);

    std::string texto_codificado = codificar(texto);
    std::string texto_decodificado = decodificar(texto_codificado);

    std::cout << "Texto Codificado: " << texto_codificado << std::endl;
    std::cout << "Texto Decodificado: " << texto_decodificado << std::endl;

    return 0;
}