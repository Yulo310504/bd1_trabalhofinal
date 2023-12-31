package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.PacoteView;

public class PacotePagamentoSqlJdbc {
    public List<PacoteView> getAllPacotes() {
        String sql = "SELECT * FROM view_pacote_reserva_hotel";
        List<PacoteView> pacotesPagamento = null;

        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            if (rs != null) {
                pacotesPagamento = new ArrayList<>();
                while (rs.next()) {
                    PacoteView p = new PacoteView();
                    p.setIdPacote(rs.getInt("id_pacote"));
                    p.setFunc(rs.getString("funcionario_criador"));
                    p.setValor(rs.getDouble("valor_reserva"));
                    p.setHotel(rs.getString("nome_hotel"));
                    p.setCheckin(rs.getDate("check_in"));
                    p.setCheckout(rs.getDate("check_out"));
                    pacotesPagamento.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacotesPagamento;
    }

    // public void createPacotePagamento(Pacote_Pagamento p) {
    // String sql = "insert into Pacote_Pagamento (idPacote, dataCriacao,
    // idPagamento, cpfCliente, valor, desconto, status, dataPagamento) values (?,
    // ?, ?, ?, ?, ?, ?, ?)";

    // try (Connection connection = new ConnectionTravel().getConnection();
    // PreparedStatement pst = connection.prepareStatement(sql);) {
    // pst.setInt(1, p.getIdPacote());
    // pst.setDate(2, p.getDataCriacao());
    // pst.setInt(3, p.getIdPagamento());
    // pst.setString(4, p.getCPFCliente().toUpperCase());
    // pst.setDouble(5, p.getValor());
    // pst.setDouble(6, p.getDesconto());
    // pst.setString(7, p.getStatus().toUpperCase());
    // pst.setDate(8, p.getDataPagamento());
    // pst.execute();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    // public Pacote_Pagamento readPacote_Pagamento(int idPagamento) {
    // String sql = "select * from Pacote_Pagamento where idPagamento = ?";
    // Pacote_Pagamento p = null;
    // ResultSet rs;
    // try (Connection connection = new ConnectionTravel().getConnection();
    // PreparedStatement pst = connection.prepareStatement(sql);) {

    // pst.setInt(1, idPagamento);
    // rs = pst.executeQuery();

    // if (rs.next()) {
    // p = new Pacote_Pagamento();
    // p.setIdPacote(rs.getInt("idPacote"));
    // p.setDataPagamento(rs.getData("dataPagamento"));
    // p.setDataCriacao(rs.getDate("dataCriacao"));
    // p.setValor(rs.getDouble("valor"));
    // p.setDesconto(rs.getDouble("desconto"));
    // p.setIdPagamento(rs.getInt("idPagamento"));
    // p.setStatus(rs.getString("status"));
    // p.setCPFCliente(rs.getString("cpfCliente"));
    // }

    // rs.close();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return p;
    // }

    // public void updatePacote_Pagamento(Pacote_Pagamento p) {
    // String sql = "update Pacote_Pagamento set dataPagamento = ?, valor = ?,
    // desconto = ?, status = ?, dataCriacao = ?, cpfCliente = ? where idPagamento =
    // ? and idPacote = ?";

    // try (Connection connection = new ConnectionTravel().getConnection();
    // PreparedStatement pst = connection.prepareStatement(sql);) {
    // pst.setDate(1, p.getDataPagamento());
    // pst.setDouble(2, p.getValor());
    // pst.setDouble(3, p.getDesconto());
    // pst.setString(4, p.getStatus().toUpperCase());
    // pst.setDate(5, p.getDataCriacao());
    // pst.setString(6, p.getCPFCliente().toUpperCase());
    // pst.setInt(7, p.getIdPagamento());
    // pst.setInt(8, p.getIdPacote());
    // pst.execute();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    // public void deletePacote_Pagamento(Pacote_Pagamento p) {
    // String sql = "delete from Pacote_Pagamento where idPagamento = ? and idPacote
    // = ?";

    // try (Connection connection = new ConnectionTravel().getConnection();
    // PreparedStatement pst = connection.prepareStatement(sql);) {

    // pst.setInt(1, cliente.getIdPagamento());
    // pst.setInt(2, cliente.getIdPacote());
    // pst.execute();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
}
