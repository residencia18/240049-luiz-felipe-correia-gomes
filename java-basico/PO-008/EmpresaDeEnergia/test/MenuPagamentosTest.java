import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Fatura;
import entidades.Imovel;
import menu.MenuPagamentos;
import util.FaturaNaoEncontrada;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuPagamentosTest {

    private List<Fatura> listaDeFaturas;
    private MenuPagamentos menuPagamentos;

    @BeforeEach
    void setUp() {
        listaDeFaturas = new ArrayList<>();
        menuPagamentos = new MenuPagamentos(listaDeFaturas);

        // Crie algumas faturas de teste
        Imovel imovelTeste = new Imovel("123", "EnderecoTeste");
        Fatura faturaTeste1 = new Fatura(imovelTeste, 50.0, 100.0, Calendar.getInstance(), 500.0);
        Fatura faturaTeste2 = new Fatura(imovelTeste, 175.0, 200.0, Calendar.getInstance(), 250.0);

        listaDeFaturas.add(faturaTeste1);
        listaDeFaturas.add(faturaTeste2);      
    }

    @Test
    void testRegistraPagamentoFaturaNaoEncontrada() {
        // Testa o caso em que a fatura não é encontrada
        assertThrows(FaturaNaoEncontrada.class,
         () -> menuPagamentos.registraPagamento(3, 100.0)); // Pagamento para uma fatura inexistente, deve retornar uma exceção
    }

    @Test
    void testRegistraPagamentoFaturaQuitada() {
        // Testa o caso em que a fatura está quitada
        Fatura fatura = listaDeFaturas.get(0);
        assertDoesNotThrow(() -> menuPagamentos.registraPagamento(fatura.getIdFatura(), 1000.0)); // Quitar fatura com valor maior que o valor da fatura
        assertTrue(fatura.isQuitado()); // Verifica se a fatura está quitada
        assertEquals(1000.0, fatura.getValorPago()); // Verifica se o valor que foi pago foi corretamente registrado na fatura
        assertEquals(500.0, menuPagamentos.getReembolsos().get(0).getValor()); // Verifica se o valor do reembolso esta correto
    }

    @Test
    void testRegistraPagamentoFaturaPendente() {
        // Testa o caso em que o pagamento registra um valor menor do que o valor total da fatura
        Fatura fatura = listaDeFaturas.get(1);
        assertDoesNotThrow(() -> menuPagamentos.registraPagamento(fatura.getIdFatura(), 100.0)); // Pagamento menor que o valor da fatura
        assertFalse(fatura.isQuitado()); // Verifica se a fatura não está quitada
        assertEquals(100.0, fatura.getValorPago()); // Verifica se o valor que foi pago foi corretamente registrado na fatura
        assertTrue(fatura.getReembolsos().isEmpty());
        assertEquals(0.0, fatura.getTotalReembolsos()); // Verifica se o valor total em reembolsos foi zero
    }
}
