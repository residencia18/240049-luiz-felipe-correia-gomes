#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <algorithm>

using namespace std;

class Livro
{
private:
    string titulo;
    string autor;
    int numCopias;

public:
    Livro(const string &titulo, const string &autor, int numCopias) : titulo(titulo), autor(autor), numCopias(numCopias)
    {
    }

    const string &getTitulo() const
    {
        return titulo;
    }

    const string &getAutor() const
    {
        return autor;
    }

    int getNumCopias() const
    {
        return numCopias;
    }

    void emprestar()
    {
        if (numCopias > 0)
        {
            numCopias--;
        }
    }

    void devolver()
    {
        numCopias++;
    }
};

class Usuario
{
private:
    string nome;
    vector<Livro *> livrosEmprestados;
    vector<time_t> tempoEmprestimo;

public:
    Usuario(const string &nome) : nome(nome)
    {
    }

    const string &getNome() const
    {
        return nome;
    }
    void emprestarLivro(Livro &livro)
    {
        if (livro.getNumCopias() > 0)
        {
            livro.emprestar();
            livrosEmprestados.push_back(&livro);
            tempoEmprestimo.push_back(time(nullptr));
        }
    }

    void devolverLivro(Livro &livro)
    {

        int pos = livrosEmprestados.size();
        for (int i = 0; i < pos; i++)
        {
            if (livrosEmprestados[i] == &livro)
            {
                livro.devolver();
                livrosEmprestados.erase(livrosEmprestados.begin() + i);
                tempoEmprestimo.erase(tempoEmprestimo.begin() + i);
            }
        }
    }

    void listarLivrosEmprestados()
    {
        if (livrosEmprestados.empty())
        {
            cout << "O usuário não tem livros emprestados." << endl;
        }
        else
        {
            cout << "Livros emprestados por " << nome << ":" << endl;
            for (size_t i = 0; i < livrosEmprestados.size(); i++)
            {
                cout << "Título: " << livrosEmprestados[i]->getTitulo() << endl;
                cout << "Autor: " << livrosEmprestados[i]->getAutor() << endl;
                cout << "Dias emprestado: " << tempoEmprestimo[i] << " dias" << endl;
                cout << "---------------------" << endl;
            }
        }
    }
};

int main()
{

    // Criando livros
    Livro livro1("As Viagens de Gulliver", "Jonathan Swift", 5);
    Livro livro2("O Pequeno Príncipe", "Antoine de Saint", 3);
    Livro livro3("1984", "George Orwell", 2);

    // Criando usuários
    Usuario usuario1("Renato Mendes");
    Usuario usuario2("Maria Clara Diniz");

    // Emprestando livros
    usuario1.emprestarLivro(livro1);
    usuario2.emprestarLivro(livro1);
    usuario2.emprestarLivro(livro2);

    // Listando livros emprestados por usuários
    usuario1.listarLivrosEmprestados();
    usuario2.listarLivrosEmprestados();

    usuario1.devolverLivro(livro1);

    return 0;
}
