package br.com.mensagem.DAO;

import br.com.mensagem.entidade.Mensagem;
import br.com.mensagem.util.FabricaConexao;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MensagemDAO {//classe onde ficam os comandos de SQL

    public void inserir(Mensagem mensagem) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst;
            if (mensagem.getCodigo() == null) {
                pst = conexao.prepareCall("INSERT INTO mensagem (codigo,nome,email,usuario,senha)"
                        + " values(null,?,?,?,?)");
            } else {
                pst = conexao.prepareCall("UPDATE mensagem set nome=?, email=?, usuario = ?, senha = ?"
                        + " where codigo=?");
                pst.setInt(5, mensagem.getCodigo());
            }
            
            pst.setString(1, mensagem.getNome());
            pst.setString(2, mensagem.getEmail());
            pst.setString(3, mensagem.getUsuario());
            pst.setString(4, mensagem.getSenha());
            pst.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Mensagem> selecionarTudo() throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) FabricaConexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM mensagem");
            ResultSet rs = pst.executeQuery();
            List<Mensagem> lista = new ArrayList<>();
            while (rs.next()) {
                Mensagem msg = new Mensagem();
                msg.setCodigo(rs.getInt("codigo"));
                msg.setNome(rs.getString("nome"));
                msg.setEmail(rs.getString("email"));
                msg.setUsuario(rs.getString("usuario"));
                msg.setSenha(rs.getString("senha"));
                lista.add(msg);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
  public void deletar(Mensagem mensagem) throws  ClassNotFoundException,SQLException{
     try{
         Connection connexao=(Connection) FabricaConexao.getConexao();
         PreparedStatement pst;
         
         if(mensagem.getCodigo()!=null){
         pst=connexao.prepareCall("DELETE FROM mensagem WHERE codigo=?;");
         pst.setInt(1,mensagem.getCodigo());
         pst.execute();
     }
         FabricaConexao.fecharConexao();
     }catch(SQLException ex){
         Logger.getLogger(MensagemDAO.class.getName()).log(Level.SEVERE,null,ex);
     }
     
   }
    
    
}
