#include <iostream>
#include <locale>

using namespace std;

int main() {
    // Set the locale to Portuguese (Brazil)
    setlocale(LC_ALL, "pt_BR.UTF-8");

    wchar_t ch;

    wcout << "Entre com um caractere: ";
    wcin >> ch;

    wcout << "O caractere: " << ch << " => " << (int)ch << endl;

    return 0;
}
