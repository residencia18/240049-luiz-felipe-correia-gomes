package eshop.aplicacao;

import java.util.ArrayList;
import java.util.List;

import eshop.entidades.Item;
import eshop.entidades.Pedido;

public class Eshop {

    public static void main(String[] args) {
        // Exemplo de um pedido com desconto à vista
        Pedido pedidoComDesconto = new Pedido(1, "123.456.789-00");
        pedidoComDesconto.adicionarItem(new Item("Produto A", 50.0));
        pedidoComDesconto.adicionarItem("Produto B", 30.0);
        pedidoComDesconto.exibirInformacoes();
        double totalComDesconto = pedidoComDesconto.calcularTotal(10); // 10% de desconto
        System.out.println("Total com desconto à vista: R$ " + totalComDesconto);

        System.out.println("\n------------------------\n");

        // Exemplo de um pedido sem desconto a prazo
        Pedido pedidoSemDesconto = new Pedido(2, "987.654.321-00");
        List<Item> itensPedido2 = new ArrayList<>();
        itensPedido2.add(new Item("Produto C", 25.0));
        itensPedido2.add(new Item("Produto D", 40.0));
        pedidoSemDesconto.adicionarItem(itensPedido2);
        pedidoSemDesconto.exibirInformacoes();
        double totalSemDesconto = pedidoSemDesconto.calcularTotal(3, 5); // 3 prestações e 5% de juro
        System.out.println("Total sem desconto a prazo: R$ " + totalSemDesconto);
    }
}
