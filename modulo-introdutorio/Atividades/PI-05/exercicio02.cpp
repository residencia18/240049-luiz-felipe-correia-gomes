#include <iostream>

int main()
{
    char ch1, ch2, ch3;

    std::cout << "Digite um caractere: ";
    std::cin >> ch1;

    std::cout << std::endl;

    /*  Você pode usar as funções isupper,
     islower e isdigit para verificar se
     um caractere é uma letra maiúscula,
     uma letra minúscula ou um dígito, respectivamente. */
    std::string type;
    if (isupper(ch1))
    {
        type = "letra maiúscula";
    }
    else if (islower(ch1))
    {
        type = "letra minúscula";
    }
    else if (isdigit(ch1))
    {
        type = "dígito";
    }
    else
    {
        type = "outro tipo de caractere";
    }

    std::cout << "O caractere digitado é " << type << std::endl;

    std::cout << std::endl;

    ch2 = 81;
    std::cout << "Caractere em formato numérico decimal: " << static_cast<int>(ch2) << std::endl;
    std::cout << "Caractere em formato numérico octal: " << std::oct << static_cast<int>(ch2) << std::endl;
    std::cout << "Caractere em formato numérico hexadecimal: " << std::hex << static_cast<int>(ch2) << std::endl;
    std::cout << "Caractere como caractere: " << ch2 << std::endl;

    std::cout << std::endl;

    // A diferença de valor entre uma letra maiúscula e sua correspondente minúscula é sempre 32.
    ch3 = ch2 + 32;
    std::cout << "Caractere em formato decimal: " << static_cast<int>(ch3) << std::endl;
    std::cout << "Caractere em formato octal: " << std::oct << static_cast<int>(ch3) << std::endl;
    std::cout << "Caractere em formato hexadecimal: " << std::hex << static_cast<int>(ch3) << std::endl;
    std::cout << "Caractere: " << ch3 << std::endl;

    return 0;
}
