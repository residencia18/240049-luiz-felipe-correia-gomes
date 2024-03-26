#include "classes.h"
#include <iostream>

using namespace std;

void CarrinhoDeCompras::adicionarProduto(const Produto &produto, int quantidade)
{
    produtos.push_back(produto);
    quantidades.push_back(quantidade);
}

void CarrinhoDeCompras::removerProduto(Produto &p, int quantidade)
{
    for (size_t i = 0; i < produtos.size(); i++)
    {
        if (produtos[i].getCodigo() == p.getCodigo())
        {
            if (quantidade >= quantidades[i])
            {
                produtos.erase(produtos.begin() + i);
                quantidades.erase(quantidades.begin() + i);
            }
            else
            {
                quantidades[i] -= quantidade;
            }
            break;
        }
    }
}

int CarrinhoDeCompras::getQuantidadeProduto(Produto &p) const
{
    for (size_t i = 0; i < produtos.size(); i++)
    {
        if (produtos[i].getCodigo() == p.getCodigo())
        {
            return quantidades[i];
        }
    }
    return 0;
}

void CarrinhoDeCompras::esvaziarCarrinho()
{
    produtos.clear();    // Limpa o vetor de produtos
    quantidades.clear(); // Limpa o vetor de quantidades
}

double CarrinhoDeCompras::calcularValorTotal() const
{
    double total = 0.0;
    for (size_t i = 0; i < produtos.size(); i++)
    {
        total += produtos[i].getPreco() * quantidades[i];
    }
    return total;
}

void CarrinhoDeCompras::exibirConteudo() const
{
    std::cout << "Carrinho de Compras:" << std::endl;
    for (size_t i = 0; i < produtos.size(); i++)
    {
        std::cout << "Produto: " << produtos[i].getNome() << " | Quantidade: " << quantidades[i] << std::endl;
    }
    std::cout << "Valor Total: R$" << calcularValorTotal() << std::endl;
}
