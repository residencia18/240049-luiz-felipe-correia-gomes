#include "classes.h"

using namespace std;

void Estoque::adicionarProduto(const Produto &produto, int quantidade)
{
    produtos.push_back(produto);
    quantidades.push_back(quantidade);
}

void Estoque::removerProduto(Produto &p, int quantidade)
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

int Estoque::getQuantidade(int codigo) const
{
    for (size_t i = 0; i < produtos.size(); i++)
    {
        if (produtos[i].getCodigo() == codigo)
        {
            return quantidades[i];
        }
    }
    return 0; // Produto não encontrado no estoque
}
