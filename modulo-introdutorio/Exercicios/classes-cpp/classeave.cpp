#include <iostream>
#include <string>
#include <locale> // Adicione esta biblioteca

using namespace std;

class Ave
{
private:
    string nome;
    int idade;
    string habitat;
    bool voa;

public:
    // Construtor da classe Ave
    Ave(string _nome, int _idade, string _habitat, bool _voa)
    {
        nome = _nome;
        idade = _idade;
        habitat = _habitat;
        voa = _voa;
        cout << "Uma ave chamada " << nome << " foi criada." << std::endl;
    }

    // Método para fazer a ave voar
    void voar()
    {
        if (voa)
        {
            cout << nome << " está voando!" << endl;
        }
        else
        {
            cout << nome << " não pode voar." << endl;
        }
    }

    void exibirInformacoes()
    {
        cout << "Nome: " << nome << ", Idade: " << idade << " anos, Habitat: " << habitat << ", Voa: " << (voa ? "Sim" : "Não") << endl;
    }
};

int main()
{
    setlocale(LC_ALL, "Portuguese"); // Configura a localização para exibir caracteres acentuados
    Ave ave1("Pardal", 3, "Ninho", true);
    Ave ave2("Galinha", 5, "Poleiro", false);

    // Chamando métodos
    ave1.voar();
    ave2.voar();
    ave1.exibirInformacoes();
    ave2.exibirInformacoes();

    return 0;
}
