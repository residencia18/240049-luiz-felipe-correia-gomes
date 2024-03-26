#include "classes.h"

using namespace std;

Produto::Produto(std::string nome, double preco, int codigo)
    : nome(nome), preco(preco), codigo(codigo) {}

std::string Produto::getNome() const
{
    return nome;
}

double Produto::getPreco() const
{
    return preco;
}

int Produto::getCodigo() const
{
    return codigo;
}
