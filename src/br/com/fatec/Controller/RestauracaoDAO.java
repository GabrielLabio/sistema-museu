package br.com.fatec.Controller;

import br.com.fatec.Model.Restauracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RestauracaoDAO {
    public void create(Restauracao r) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("INSERT INTO restauracao (tombo, descricao) VALUES (?, ?)");
            stmt.setInt(1, r.getTombo());
            stmt.setString(2, r.getDescricao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
        
    } 
}
