package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import entidades.Imovel;
import servicos.ImovelService;
import util.ImovelNaoEncontradoException;

public class ClienteDAO {

    public static boolean criar(Cliente cliente) {
        String query = "INSERT INTO Cliente (nome, propriedade, cpf) VALUES (?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getPropriedade().getMatricula());
            statement.setString(3, cliente.getCpf());
            statement.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Cliente> retornarTodos(ImovelService imovelService) {
        String query = "SELECT nome, propriedade, cpf FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String matriculaDoImovel = resultSet.getString("propriedade");
                String cpf = resultSet.getString("cpf");

                Imovel propriedade = imovelService.retornarPelaMatricula(matriculaDoImovel);

                Cliente cliente = new Cliente(nome, cpf, propriedade);
                clientes.add(cliente);        
            }
            return clientes;
        } catch (SQLException e) {
            return null;
        } catch (ImovelNaoEncontradoException e) {
            return null;
        }
    }

    public static Cliente retornarPeloCPF(String cpf, ImovelService imovelService) {
        String query = "SELECT cpf, nome, propriedade FROM Cliente WHERE cpf = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String matriculaDoImovel = resultSet.getString("propriedade");

                Imovel propriedade = imovelService.retornarPelaMatricula(matriculaDoImovel);
                return new Cliente(nome, cpf, propriedade);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } catch (ImovelNaoEncontradoException e) {
            return null;
        }
    }

    public static boolean atualizar(Cliente cliente) {
        String query = "UPDATE Cliente SET nome = ?, propriedade = ? WHERE cpf = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getPropriedade().getMatricula());
            statement.setString(3, cliente.getCpf());
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deletar(String cpf) {
        String query = "DELETE FROM Cliente WHERE cpf = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cpf);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}