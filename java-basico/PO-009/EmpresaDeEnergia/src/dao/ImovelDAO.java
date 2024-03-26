package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Imovel;

public class ImovelDAO {

    public static boolean criar(Imovel imovel) {
        String query = "INSERT INTO Imovel (endereco, leituraAtual, leituraAnterior, matricula) VALUES (?, ?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, imovel.getEndereco());
            statement.setString(2, imovel.getLeituraAtual().toString());
            statement.setString(3, imovel.getLeituraAnterior().toString());
            statement.setString(4, imovel.getMatricula());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Imovel> retornarTodos() {
        String query = "SELECT matricula, endereco, leituraAtual, leituraAnterior FROM Imovel";
        List<Imovel> imoveis = new ArrayList<>();

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String matricula = resultSet.getString("matricula");
                String endereco = resultSet.getString("endereco");
                String leitura1 = resultSet.getString("leituraAtual");
                String leitura2 = resultSet.getString("leituraAnterior");

                // Converter leituras para double
                double leituraAtual = Double.parseDouble(leitura1);
                double leituraAnterior = Double.parseDouble(leitura2);

                Imovel imovel = new Imovel(matricula, endereco, leituraAtual, leituraAnterior);
                imoveis.add(imovel);
            }
            return imoveis;
        } catch (SQLException e) {
            return null;
        }
    }

    public static Imovel retornarPelaMatricula(String matricula) {
        String query = "SELECT matricula, endereco, leituraAtual, leituraAnterior FROM Imovel WHERE matricula = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, matricula);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String endereco = resultSet.getString("endereco");
                String leitura1 = resultSet.getString("leituraAtual");
                String leitura2 = resultSet.getString("leituraAnterior");

                // Converter leituras para double
                double leituraAtual = Double.parseDouble(leitura1);
                double leituraAnterior = Double.parseDouble(leitura2);

                return new Imovel(matricula, endereco, leituraAtual, leituraAnterior);
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean atualizar(Imovel imovel) {
        String query = "UPDATE Imovel SET endereco = ? WHERE matricula = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, imovel.getEndereco());
            statement.setString(2, imovel.getMatricula());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deletar(String matricula) {
        String query = "DELETE FROM Imovel WHERE matricula = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, matricula);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}