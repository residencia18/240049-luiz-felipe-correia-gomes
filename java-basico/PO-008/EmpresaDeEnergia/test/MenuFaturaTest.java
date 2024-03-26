import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Fatura;
import entidades.Imovel;
import menu.MenuFatura;
import util.ImovelNaoEncontrado;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class MenuFaturaTest {

    private List<Fatura> faturas;
    private List<Imovel> imoveis;
    private MenuFatura menuFatura;

    @BeforeEach
    void setUp() {
        faturas = new ArrayList<>();
        imoveis = new ArrayList<>();
        menuFatura = new MenuFatura(faturas, imoveis);
    }

    @Test
    void testImovelNaoEncontrado() {
        // Criação de um imóvel de teste
        imoveis.add(new Imovel("123", "EnderecoTeste")); 
        assertThrows(ImovelNaoEncontrado.class, () -> menuFatura.registraLeitura(imoveis, "456")); // Procura um imóvel inexistente, deve retornar uma exceção
    }

    @Test
    void testCalculoCorreto() {
        Imovel imovelTeste = new Imovel("123", "EnderecoTeste"); // Criação de um imóvel de teste
        imovelTeste.setLeituraAnterior(50.0);

        imoveis.add(imovelTeste);

        // Criação de uma leitura de teste
        imovelTeste.setLeituraAtual(100.0);
        try {
            menuFatura.registraLeitura(imoveis, "123");
        } catch (ImovelNaoEncontrado e) {
            e.printStackTrace();
        }

        assertEquals(1, faturas.size()); // Verifica se uma fatura foi criada
        Fatura fatura = faturas.get(0);

        // Verifica se o cálculo da fatura está correto
        assertEquals(50.0, fatura.getPenultimaLeitura());
        assertEquals(100.0, fatura.getUltimaLeitura());
        assertEquals(500.0, fatura.getValorCalculado()); // (100 - 50) * 10 (custo por KWh)
    }

    @Test
    void testImovelAtualizaLeitura() {
        Imovel imovelTeste = new Imovel("789", "EnderecoTeste"); // Criação de um imóvel de teste
        imovelTeste.setLeituraAnterior(50.0);

        imoveis.add(imovelTeste);

        // Criação de uma leitura de teste
        imovelTeste.setLeituraAtual(100.0);
        try {
            menuFatura.registraLeitura(imoveis, "789");
        } catch (ImovelNaoEncontrado e) {
            e.printStackTrace();
        }

        // Verifica se a leitura do imóvel foi atualizada corretamente
        assertEquals(100.0, imovelTeste.getLeituraAnterior()); // A leitura anterior deve ser atualizada
        assertEquals(100.0, imovelTeste.getLeituraAtual()); 
    }
}
