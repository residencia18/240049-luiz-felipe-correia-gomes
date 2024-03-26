package entidades;

import utils.CartaoEnum;
import utils.RegistroInterface;

public class Passageiro implements RegistroInterface {

    private String nome;
    private String cpf;
    private CartaoEnum cartao;
    private String numCartao;


    public Passageiro(String nome, String cpf, CartaoEnum cartao, String numCartao) {
        this.nome = nome;
        this.cpf = cpf;
        this.cartao = cartao;
        this.numCartao = numCartao;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public CartaoEnum getCartao() {
        return cartao;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setCartao(CartaoEnum cartao) {
        this.cartao = cartao;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    @Override
    public String toString() {
        return nome + ";" + cpf + ";" + cartao + ";" + numCartao;
    }
}
