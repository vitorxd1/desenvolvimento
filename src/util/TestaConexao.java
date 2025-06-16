package util;
import java.sql.Connection;
/**
 *
 * @author Senac
 */
public class TestaConexao {
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        try {
            // Estabele conexão
            Connection conn = ConexaoSQL.conectar();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexao Aberta");
            }
            
            //Desconectar
            ConexaoSQL conexao = new ConexaoSQL();
            conexao.conn = conn;
            boolean desconectou = conexao.desconectar();
            
            if (desconectou && conn.isClosed()) {
                System.out.println("Conexao Fechada");
            }else {
                System.err.println("Falha ao fechar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
