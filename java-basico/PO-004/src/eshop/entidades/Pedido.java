package eshop.entidades;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numeroPedido;
    private String cpfDoCliente;
    private List<Item> itens;
    
    public Pedido (int numeroPedido, String cpfDoCliente) {
        this.numeroPedido = numeroPedido;
        this.cpfDoCliente = cpfDoCliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem (Item item) {
        this.itens.add(item);
    }

    public void adicionarItem(String nome, double preco) {
        this.itens.add(new Item(nome, preco));
    }

    public void adicionarItem(List<Item> itens) {
        this.itens.addAll(itens);
    }

    // Calcular o valor total do pedido à vista
    public double calcularTotal(double descontoPercentual) {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        double desconto = total * (descontoPercentual / 100);
        return total - desconto;
    }

    // Calcular o valor total do pedido a prazo
    public double calcularTotal(int numPrestacoes, double juroPorcentual) {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        double juros = total * (juroPorcentual / 100);
        return total + juros;
    }

    // Mostrar informações
    public void exibirInformacoes() {
        System.out.println("Numero do Pedido: " + numeroPedido);
        System.out.println("CPF do Cliente: " + cpfDoCliente);
        System.out.println("Itens do Pedido: ");
        for (Item item : itens) {
            System.out.println(item.getNome() + ": " + item.getPreco());
        }
    }
}




