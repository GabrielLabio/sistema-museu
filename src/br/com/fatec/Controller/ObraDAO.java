package br.com.fatec.Controller;

import br.com.fatec.Model.Exposicao;
import br.com.fatec.Model.Obra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ObraDAO {
    public void create(Obra o) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("INSERT INTO obra (autor,titulo,dataCriacao,descricao,emExposicao)VALUES(?,?,?,?,?)");
            stmt.setString(1, o.getAutor());
            stmt.setString(2, o.getTitulo());
            stmt.setString(3, o.getDataCriacao());
            stmt.setString(4, o.getDescricao());
            stmt.setString(5, o.getEmExposicao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
        
    } 
    
    public List<Obra> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Obra> obras = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM obra");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Obra obra = new Obra();
                obra.setTombo(rs.getInt("tombo"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setAutor(rs.getString("autor"));
                obra.setDescricao(rs.getString("descricao"));
                obra.setDataCriacao(rs.getString("dataCriacao"));
                
                obras.add(obra);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar as Obras");
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return obras;
    }
    
    public void update (Obra o) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
    
        
        try {
            stmt = con.prepareStatement("UPDATE obra SET titulo = ?, autor = ?, dataCriacao = ?, descricao = ? WHERE tombo = ?");
            stmt.setString(1, o.getTitulo());
            stmt.setString(2, o.getAutor());
            stmt.setString(3, o.getDataCriacao());
            stmt.setString(4, o.getDescricao());
            stmt.setInt(5, o.getTombo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete (Obra o) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;    
        
        try {
            stmt = con.prepareStatement("DELETE FROM obra WHERE tombo = ?");
            stmt.setInt(1, o.getTombo());            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
        
        
}
