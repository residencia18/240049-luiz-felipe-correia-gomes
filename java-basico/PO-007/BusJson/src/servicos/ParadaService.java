package servicos;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entidades.Parada;
import utils.CadastroInvalidoException;
import utils.DuplicataException;

public class ParadaService {

    private Set<Parada> paradas;

    public ParadaService() {
        this.paradas = new HashSet<>();
    }

    public void adicionarParada(Parada parada) {
        paradas.add(parada);
    }

    public Parada criar(Scanner scanner) {
        Parada parada = new Parada(scanner.nextLine());
        try {
            validarParada(parada);
            paradas.add(parada);
        } catch (CadastroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        return parada;
    }

    public Parada criar(String nome) throws DuplicataException, CadastroInvalidoException {
        Parada parada = new Parada(nome);
        validarParada(parada);
        paradas.add(parada);
        return parada;
    }

    public Set<Parada> getCadastros() {
        return paradas;
    }

    public boolean validarParada(Parada parada)
            throws CadastroInvalidoException {
        if (parada.getNome() == null || parada.getNome().isEmpty()) {
            throw new CadastroInvalidoException("Parada inválida");
        }

        return true;
    }
}
