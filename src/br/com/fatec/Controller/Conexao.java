package br.com.fatec.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbmuseu";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Falha na conexão com o banco de dados: ", ex);
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if(con != null) {
                con.close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Falha ao fechar o banco de dados: ", ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if(con != null) {
                stmt.close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(" ", ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if(rs != null) {
                rs.close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(" ", ex);
        }
    }
    
}
