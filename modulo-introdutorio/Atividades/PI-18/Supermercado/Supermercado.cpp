#include <iostream>
#include "classes.h"

int main()
{
    Produto p1("Maçã", 2.5, 1);
    Produto p2("Arroz", 10.0, 2);
    Produto p3("Leite", 4.0, 3);

    CarrinhoDeCompras carrinho;
    carrinho.adicionarProduto(p1, 3);
    carrinho.adicionarProduto(p2, 2);
    carrinho.adicionarProduto(p3, 1);

    double valorTotal = carrinho.calcularValorTotal();
    cout << "Valor total da compra: " << valorTotal << endl;
    // Resposta: Valor total da compra: 31.5

    carrinho.removerProduto(p2, 1);
    valorTotal = carrinho.calcularValorTotal();
    cout << "Valor total após remoção: " << valorTotal << endl;
    // Resposta: Valor total após remoção: 21.5

    carrinho.esvaziarCarrinho();
    valorTotal = carrinho.calcularValorTotal();
    cout << "Valor total após esvaziar o carrinho: " << valorTotal << endl;
    // Resposta: Valor total após esvaziar o carrinho: 0.0

    Produto p4("Chocolate", 3.0, 4);
    carrinho.adicionarProduto(p4, 10); // Supondo estoque limitado a 5 unidades
    cout << "Quantidade de chocolates no carrinho: " << carrinho.getQuantidadeProduto(p4) << endl;
    // Resposta: Quantidade de chocolates no carrinho: 5

    carrinho.adicionarProduto(p1, 2);
    carrinho.adicionarProduto(p2, 3);
    carrinho.adicionarProduto(p3, 1);
    carrinho.adicionarProduto(p4, 2);
    carrinho.exibirConteudo();
    // Resposta: Carrinho de Compras:
    // - Maçã (2.5) x 2
    // - Arroz (10.0) x 3
    // - Leite (4.0) x 1
    // - Chocolate (3.0) x 2

    // Encerrar programa
    while (true)
    {
        char opcao;
        cout << "Encerrar programa de teste? (s/n)" << endl;
        cin >> opcao;

        if (opcao == 's' || opcao == 'S')
        {
            break;
        }
    }

    return 0;
}
