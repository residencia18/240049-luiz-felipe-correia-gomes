#ifndef PRODUTO_H
#define PRODUTO_H
#ifndef ESTOQUE_H
#define ESTOQUE_H
#ifndef CARRINHODECOMPRAS_H
#define CARRINHODECOMPRAS_H

#include <string>
#include <vector>

using namespace std;

class Produto
{
private:
    string nome;
    double preco;
    int codigo;

public:
    Produto(string nome, double preco, int codigo);
    string getNome() const;
    double getPreco() const;
    int getCodigo() const;
};

class Estoque
{
public:
    void adicionarProduto(const Produto &produto, int quantidade);
    void removerProduto(Produto &p, int quantidade);
    int getQuantidade(int codigo) const;

private:
    vector<Produto> produtos;
    vector<int> quantidades;
};

class CarrinhoDeCompras
{
public:
    void adicionarProduto(const Produto &p, int quantidade);
    void removerProduto(Produto &p, int quantidade);
    int getQuantidadeProduto(Produto &p) const;
    void esvaziarCarrinho();
    double calcularValorTotal() const;
    void exibirConteudo() const;

private:
    vector<Produto> produtos;
    vector<int> quantidades;
};

#endif
#endif
#endif