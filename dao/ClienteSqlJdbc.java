package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Cliente;

public class ClienteSqlJdbc {
    public List<Cliente> getAllClientes() {
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = null;

        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            if (rs != null) {
                clientes = new ArrayList<>();
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    clientes.add(c);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void createCliente(Cliente cliente) {
        String sql = "insert into Cliente (nome, cpf, telefone, email) values (?, ?, ?, ?)";

        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {
            pst.setString(1, cliente.getNome().toUpperCase());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getTelefone());
            pst.setString(4, cliente.getEmail());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente readCliente(int idCliente) {
        String sql = "select * from Cliente where idCliente = ?";
        Cliente cliente = null;
        ResultSet rs;
        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setInt(1, idCliente);
            rs = pst.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataAtendimento(rs.getDate("dataAtendimento"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setIdAtendimento(rs.getInt("idAtendimento"));
                cliente.setEmail(rs.getString("email"));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public void updateCliente(Cliente cliente) {
        String sql = "update Cliente set nome = ?, cpf = ?, dataAtendimento = ?, telefone = ?, idAtendimento = ?, email = ? where idCliente = ?";

        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {
            pst.setString(1, cliente.getNome().toUpperCase());
            pst.setString(2, cliente.getCpf().toUpperCase());
            pst.setDate(3, cliente.getDataAtendimento());
            pst.setString(4, cliente.getTelefone().toUpperCase());
            pst.setInt(5, cliente.getIdAtendimento());
            pst.setString(6, cliente.getEmail().toUpperCase());
            pst.setInt(7, cliente.getIdCliente());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(Cliente cliente) {
        String sql = "delete from Cliente where idCliente = ?";

        try (Connection connection = new ConnectionTravel().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setInt(1, cliente.getIdCliente());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
