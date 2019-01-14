
package br.com.fatec.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class UsuarioDAO {
    
    public boolean checkLogin(String login, String senha){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()){
                check = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao checar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
        
        return check;
    }
    
    public void criarLogin(String login, String senha){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        // Validar login e senha vazio
        
        try {
            stmt = con.prepareStatement("INSERT INTO usuario (login, senha) VALUES (?, ?)");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
        public void excluirLogin(String login, String senha){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        // Validar login e senha vazio
        
        try {
            stmt = con.prepareStatement("DELETE login, senha from usuario where usuario = ?");
            stmt.setString(1, login);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar: " + ex);   
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }
    
}
