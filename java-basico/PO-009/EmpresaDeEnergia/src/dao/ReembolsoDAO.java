package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entidades.Reembolso;
import servicos.FaturaService;
import util.GerenciadorDeData;

public class ReembolsoDAO {

    public static boolean criar(Reembolso reembolso) {
        String query = "INSERT INTO Reembolso (idFatura, valor, data) VALUES (?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reembolso.getIdFatura());
            statement.setString(2, reembolso.getValor().toString());
            statement.setString(3, GerenciadorDeData.calendarParaString(reembolso.getData()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Reembolso> retornarTodos(FaturaService faturaService) {
        String query = "SELECT idFatura, valor, data FROM Reembolso";
        List<Reembolso> reembolsos = new ArrayList<>();

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String idFatura = resultSet.getString("idFatura");
                String valor = resultSet.getString("valor");
                String data = resultSet.getString("data");

                // Converter valores
                double valorPagamento = Double.parseDouble(valor);
                Calendar dataPagamento = GerenciadorDeData.stringParaCalendar(data);
                
                Reembolso reembolso = new Reembolso(idFatura, valorPagamento, dataPagamento);
                reembolsos.add(reembolso);
            }

            return reembolsos;
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Reembolso retornarPelaID(String id) {
        String query = "SELECT idFatura, valor, data FROM Reembolso WHERE idFatura = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String idFatura = resultSet.getString("idFatura");
                String valor = resultSet.getString("valor");
                String data = resultSet.getString("data");

                // Converter valores
                double valorReembolso = Double.parseDouble(valor);
                Calendar dataReembolso = GerenciadorDeData.stringParaCalendar(data);

                return new Reembolso(idFatura, valorReembolso, dataReembolso);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean atualizar(Reembolso reembolso) {
        String query = "UPDATE Reembolso SET valor = ?, data = ? WHERE idFatura = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reembolso.getValor().toString());
            statement.setString(2, GerenciadorDeData.calendarParaString(reembolso.getData()));
            statement.setString(3, reembolso.getIdFatura());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deletar(String idFatura) {
        String query = "DELETE FROM Reembolso WHERE idFatura = ?";

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idFatura);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}