#include <iostream>

int main() {
    char ch1, ch2, ch3;

    std::cout << "Digite dois caracteres: ";
    std::cin >> ch1 >> ch2;

    ch3 = ch1 - 1;
    if (!isprint(ch3)) {
        ch3 = '_';
    }

    std::cout << "Caractere anterior a ch1: " << static_cast<int>(ch3) << " (decimal), "
              << std::oct << static_cast<int>(ch3) << " (octal), "
              << std::hex << static_cast<int>(ch3) << " (hexadecimal), "
              << ch3 << " (caractere)" << std::endl;

    ch3 = ch2 - 1;
    if (!isprint(ch3)) {
        ch3 = '_';
    }

    printf("Caractere anterior a ch2: %d (decimal), %o (octal), %x (hexadecimal), %c (caractere)\n",
           static_cast<int>(ch3), static_cast<int>(ch3), static_cast<int>(ch3), ch3);

    ch3 = (ch1 >= 'A' && ch1 <= 'Z') ? 'A' : ' ';
    std::cout << "Valor de ch3 se ch1 for uma letra maiúscula: " << ch3 << std::endl;

    ch3 = (ch2 >= 'a' && ch2 <= 'z') ? 'a' : ' ';
    std::cout << "Valor de ch3 se ch2 for uma letra minúscula: " << ch3 << std::endl;

    ch3 = (isdigit(ch1) || isdigit(ch2)) ? '1' : ' ';
    std::cout << "Valor de ch3 se ch1 ou ch2 for um dígito: " << ch3 << std::endl;

    return 0;
}