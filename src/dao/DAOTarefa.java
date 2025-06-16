package dao;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModTarefas;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import util.ConexaoSQL;
/**
 *
 * @author Senac
 */
public class DAOTarefa extends ConexaoSQL{
    
    public int salvarDAOTarefa(ModTarefas pTarefa){
        
        try {
            conn = ConexaoSQL.conectar(); //Tenta conectar
            String sql = "INSERT INTO tb_tarefas("
                    + " tarefa,"
                    + " descricao,"
                    + " data_tarefa,"
                    + " hora_inicio,"
                    + " hora_fim,"
                    + " status"
                    + ") VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)){
               pstm.setString(1, pTarefa.getTarefas());
               pstm.setString(2, pTarefa.getDescricao());
               pstm.setDate(3, java.sql.Date.valueOf(pTarefa.getDataTarefa()));
               pstm.setTime(4, java.sql.Time.valueOf(pTarefa.getHoraInicio()));
               pstm.setTime(5, java.sql.Time.valueOf(pTarefa.getHoraFim()));
               pstm.setString(6, pTarefa.getStatus());
               pstm.executeUpdate();
               
               try(ResultSet rs = pstm.getGeneratedKeys()){
                   if (rs.next()) {
                       int idGerado = rs.getInt(1);
                       pTarefa.setId(idGerado); //Atualiza o objeto com ID gerado
                       return idGerado;
                   }
               }
       
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            } finally {
                this.desconectar();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE,null,ex);
        } 
      return 0;
    }
    
  /**
   * Retorna lista de tarefas
   * @return 
   */
  public ArrayList<ModTarefas> listarDAOTarefa(){
      ArrayList<ModTarefas> listaTarefas = new ArrayList<>();
      try {
            conn = ConexaoSQL.conectar();
            String sql = "SELECT * FROM tb_tarefas";
            try (PreparedStatement pstm = criarPreparedStatement(sql, 0);
                    ResultSet rs = pstm.executeQuery()){
                while (rs.next()) {                    
                    ModTarefas pModTarefas = new ModTarefas();
                    pModTarefas.setId(rs.getInt("id"));
                    pModTarefas.setTarefas(rs.getString("tarefa"));
                    pModTarefas.setDescricao(rs.getString("descricao"));
                    pModTarefas.setDataTarefa(rs.getDate("data_tarefa").toLocalDate());
                    pModTarefas.setHoraInicio(rs.getTime("hora_Inicio").toLocalTime());
                    pModTarefas.setHoraFim(rs.getTime("hora_Fim").toLocalTime());
                    pModTarefas.setStatus(rs.getString("status"));
                    listaTarefas.add(pModTarefas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                this.desconectar();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
      return listaTarefas;
 }


  
  /**
   * Metodo para atualizar uma tarefa no banco de dados
   */
  public boolean editarTarefaDAO(ModTarefas pModTarefa){
        try {
            conn = ConexaoSQL.conectar();
            
            String sql = ("UPDATE tb_tarefas SET tarefa = ?,"
                    + "descricao = ?,"
                    + "data_tarefa = ?,"
                    + "hora_inicio = ?,"
                    + "hora_fim = ?,"
                    + "status = ?"
                    + " WHERE id = '"+pModTarefa.getId()+"'");
            try(PreparedStatement pstm = criarPreparedStatement(sql, 0)) {
                pstm.setString(1, pModTarefa.getTarefas());
                pstm.setString(2, pModTarefa.getDescricao());
                pstm.setDate(3, java.sql.Date.valueOf(pModTarefa.getDataTarefa()));
                pstm.setTime(4, java.sql.Time.valueOf(pModTarefa.getHoraInicio()));
                pstm.setTime(5, java.sql.Time.valueOf(pModTarefa.getHoraFim()));
                pstm.setString(6, pModTarefa.getStatus());
                pstm.executeUpdate();
               //int linhasAfetadas = pstm.executeUpdate();
               //return linhasAfetadas > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.desconectar();
                return true;
                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
  }
  
  public boolean excluirTarefaDAO(int id){
      
        try {
            conn = ConexaoSQL.conectar();
            
            String sql = "DELETE FROM tb_tarefas WHERE id = ?;";
            
            try (PreparedStatement pstm = criarPreparedStatement(sql, 0)){
                pstm.setInt(1, id);
                int linhasAfetadas = pstm.executeUpdate();
                
                return linhasAfetadas > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.desconectar();
            }
  
        } catch (ClassNotFoundException ex) {
           
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
      return false;
  }


    public ArrayList<ModTarefas> listarTarefasDataDAO(LocalDate dataT) {
        ArrayList<ModTarefas> lista = new ArrayList<>();

    try {

        conn = ConexaoSQL.conectar();

        String sql = "SELECT * FROM tb_tarefas WHERE data_tarefa = ? ORDER BY hora_inicio";

        try(PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setDate(1, java.sql.Date.valueOf(dataT));

            try(ResultSet rs = pstm.executeQuery()) {

                while (rs.next()) {

                    ModTarefas t = new ModTarefas();

                    t.setId(rs.getInt("id"));
                    t.setTarefas(rs.getString("tarefa"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setDataTarefa(rs.getDate("data_tarefa").toLocalDate());
                    t.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    t.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                    t.setStatus(rs.getString("status"));

                    lista.add(t);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

    } catch (ClassNotFoundException ex) {

        Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);

    }

    return lista;
 
    }

    public ArrayList<LocalDate> listarDatasTarefasDAO() {
        ArrayList<LocalDate> lista = new ArrayList<>();
        
        try {
            conn = ConexaoSQL.conectar();
            
            String sql = "SELECT DISTINCT data_tarefa FROM tb_tarefas ORDER BY data_tarefa";
            try(PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {                    
                    lista.add(rs.getDate("data_tarefa").toLocalDate());
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<ModTarefas> listarTarefasPorIntervalo(LocalDate dataInicio, LocalDate dataFim) {
       ArrayList<ModTarefas> listar = new ArrayList<>();
       
        try {
            conn = ConexaoSQL.conectar();
            String sql = "SELECT * FROM tb_tarefas WHERE data_tarefa BETWEEN ? AND ? ORDER BY data_tarefa, hora_inicio";
            try(PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setDate(1, java.sql.Date.valueOf(dataInicio));
                pstm.setDate(2, java.sql.Date.valueOf(dataFim));
                try(ResultSet rs = pstm.executeQuery()) {
                    while(rs.next()) {
                        ModTarefas modTarefas = new ModTarefas();
                        
                        modTarefas.setId(rs.getInt("id"));
                        modTarefas.setTarefas(rs.getString("tarefa"));
                        modTarefas.setDescricao(rs.getString("descricao"));
                        modTarefas.setDataTarefa(rs.getDate("data_tarefa").toLocalDate());
                        modTarefas.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                        modTarefas.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                        modTarefas.setStatus(rs.getString("status"));
                        listar.add(modTarefas);
                    }
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  listar;
    }
}
