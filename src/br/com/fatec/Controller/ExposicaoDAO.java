package br.com.fatec.Controller;

import br.com.fatec.Model.Exposicao;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ExposicaoDAO {
    
    public void create (Exposicao e) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
    
        
        try {
            stmt = con.prepareStatement("INSERT INTO exposicao (nome,tipo,dataIni,dataFim,horaIni,horaFim,salas)VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getTipo());
            stmt.setString(3, e.getDataIni());
            stmt.setString(4, e.getDataFim());
            stmt.setString(5, e.getHoraIni());
            stmt.setString(6, e.getHoraFim());
            stmt.setString(7, e.getSalas());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void update (Exposicao e) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
    
        
        try {
            stmt = con.prepareStatement("UPDATE exposicao SET nome = ?, tipo = ?, dataIni = ?, dataFim = ?, horaIni = ?, horaFim = ?, salas = ? WHERE codigoExpo = ?");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getTipo());
            stmt.setString(3, e.getDataIni());
            stmt.setString(4, e.getDataFim());
            stmt.setString(5, e.getHoraIni());
            stmt.setString(6, e.getHoraFim());
            stmt.setString(7, e.getSalas());
            stmt.setInt(8, e.getCodigoExpo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete (Exposicao e) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
    
        
        try {
            stmt = con.prepareStatement("DELETE FROM exposicao WHERE codigoExpo = ?");
            stmt.setInt(1, e.getCodigoExpo());            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public Exposicao pesquisar(Exposicao pesq)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM exposicao "
                + "WHERE codigoExpo ="+pesq.getCodigoExpo();

        //abre o banco
        PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
        
        
        //executar comando SQL
        ResultSet rs = stmt.executeQuery();
        //verifica se encontrou o genero
        if (rs.next()) { //encontrou
            
            pesq.setCodigoExpo(rs.getInt("codigoExpo"));
            pesq.setNome(rs.getString("nome"));
            pesq.setTipo(rs.getString("tipo"));
            pesq.setDataIni(rs.getString("dataIni"));
            pesq.setDataFim(rs.getString("dataFim"));
            pesq.setHoraIni(rs.getString("horaIni"));
            pesq.setHoraFim(rs.getString("horaFim"));
        }
        rs.close(); //fecha o resultSet
        //con.closeConnection(con, stmt);
        return pesq;
    }
    
    public List<Exposicao> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Exposicao> exposicoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM exposicao");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Exposicao expo = new Exposicao();
                expo.setCodigoExpo(rs.getInt("codigoExpo"));
                expo.setNome(rs.getString("nome"));
                expo.setTipo(rs.getString("tipo"));
                expo.setDataIni(rs.getString("dataIni"));
                expo.setDataFim(rs.getString("dataFim"));
                expo.setHoraIni(rs.getString("horaIni"));
                expo.setHoraFim(rs.getString("horaFim"));
                expo.setSalas(rs.getString("salas"));
                exposicoes.add(expo);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar as Exposições");
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return exposicoes;
    }
}
