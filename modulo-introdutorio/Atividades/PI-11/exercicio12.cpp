#include <iostream>
#include <string>

std::string caracteres_comuns(const std::string &A, const std::string &B)
{
    std::string C;

    for (char c : A)
    {
        if (B.find(c) != std::string::npos && C.find(c) == std::string::npos)
        {
            C += c;
        }
    }

    return C;
}

int main()
{
    std::string A, B;

    std::cout << "Digite a primeira string (A): ";
    std::cin >> A;

    std::cout << "Digite a segunda string (B): ";
    std::cin >> B;

    std::string C = caracteres_comuns(A, B);

    if (C.empty())
    {
        std::cout << "Não há caracteres comuns entre A e B." << std::endl;
    }
    else
    {
        std::cout << "Caracteres comuns entre A e B: " << C << std::endl;
    }

    return 0;
}