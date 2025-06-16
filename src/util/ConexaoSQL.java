/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Senac
 */
public class ConexaoSQL {

    public Connection conn; //Variável que recebe a conexão.
    // Classe para conectar ao banco

    public static Connection conectar() throws ClassNotFoundException {

        Connection conn = null;
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bd_controletarefas";
        String userName = "root";
        String password = "Senha@123";

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexao feita com sucesso!");
            return conn;
        } catch (SQLException ex) {
            System.err.println("Conexao falhou");
            return null;
        }
    }

    // Classe para preparar o banco para receber inserção
    public PreparedStatement criarPreparedStatement(String pSQL, int RETURN_GENERATED_KEYS) {

        try {
            return conn.prepareStatement(pSQL, RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    //Classe para desconectar do banco de dados
    public boolean desconectar() {
        try {
            if (!this.conn.isClosed()) {
                this.conn.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
