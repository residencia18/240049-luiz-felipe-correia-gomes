package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entidades.Fatura;
import entidades.Imovel;
import servicos.ImovelService;
import util.GerenciadorDeData;
import util.ImovelNaoEncontradoException;

public class FaturaDAO {

    public static boolean criar(Fatura fatura) {
        String query = "INSERT INTO Fatura (idFatura, imovelAssociado, penultimaLeitura, ultimaLeitura, data, valor) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataAcessObject.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fatura.getIdFatura());
            statement.setString(2, fatura.getImovelAssociado().getMatricula());
            statement.setString(3, fatura.getPenultimaLeitura().toString());
            statement.setString(4, fatura.getUltimaLeitura().toString());
            statement.setString(5, GerenciadorDeData.calendarParaString(fatura.getData()));
            statement.setString(6, fatura.getValor().toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar fatura: " + e.getMessage());
            return false;
        }
    }

    public static List<Fatura> retornarTodos(ImovelService imovelService) {
        String query = "SELECT idFatura, imovelAssociado, penultimaLeitura, ultimaLeitura, data, valor FROM Fatura";
        List<Fatura> faturas = new ArrayList<>();

        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String idFatura = resultSet.getString("idFatura");
                String matricula = resultSet.getString("imovelAssociado");
                String leitura1 = resultSet.getString("penultimaLeitura");
                String leitura2 = resultSet.getString("ultimaLeitura");
                String data = resultSet.getString("data");
                String valor = resultSet.getString("valor");

                // Converter valores
                Imovel imovel = imovelService.retornarPelaMatricula(matricula);
                double penultimaLeitura = Double.parseDouble(leitura1);
                double ultimaLeitura = Double.parseDouble(leitura2);
                Calendar dataFatura = GerenciadorDeData.stringParaCalendar(data);                
                double valorFatura = Double.parseDouble(valor);
                
                Fatura fatura = new Fatura(idFatura, imovel, penultimaLeitura, ultimaLeitura, dataFatura, valorFatura);
                faturas.add(fatura);
            }

            return faturas;
        } catch (SQLException e) {
            return null;
        } catch (ImovelNaoEncontradoException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Fatura retornarPelaID(String id, ImovelService imovelService) {
        String query = "SELECT imovelAssociado, penultimaLeitura, ultimaLeitura, data, valor FROM Fatura WHERE idFatura = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String matricula = resultSet.getString("imovelAssociado");
                String leitura1 = resultSet.getString("penultimaLeitura");
                String leitura2 = resultSet.getString("ultimaLeitura");
                String data = resultSet.getString("data");
                String valor = resultSet.getString("valor");

                // Converter valores
                Imovel imovel = imovelService.retornarPelaMatricula(matricula);
                double penultimaLeitura = Double.parseDouble(leitura1);
                double ultimaLeitura = Double.parseDouble(leitura2);
                Calendar dataFatura = GerenciadorDeData.stringParaCalendar(data);                
                double valorFatura = Double.parseDouble(valor);

                return new Fatura(imovel, penultimaLeitura, ultimaLeitura, dataFatura, valorFatura);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } catch (ImovelNaoEncontradoException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean atualizar(Fatura fatura) {
        String query = "uPDATE Fatura SET valorPago = ? WHERE idFatura = ?";
        try (Connection connection = DataAcessObject.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fatura.getValorPago().toString());
            statement.setString(2, fatura.getIdFatura());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fatura: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletar(String idFatura) {
        String query = "DELETE FROM Fatura WHERE idFatura = ?";

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
