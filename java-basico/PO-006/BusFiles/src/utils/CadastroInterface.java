package utils;

import java.util.Scanner;

public interface CadastroInterface {
    
    public void cadastrar(Scanner scanner);   
    public void alterar(Scanner scanner) throws ListaVaziaException;
    public void excluir(Scanner scanner) throws ListaVaziaException; 
    public void exibir() throws ListaVaziaException;
    public void salvar();
    public void carregar();
}
