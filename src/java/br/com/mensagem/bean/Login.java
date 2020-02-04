/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mensagem.bean;

import br.com.mensagem.entidade.Mensagem;
import br.com.mensagem.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
/**
 *
 * @author Joe
 */
public class Login {
    
    public String sair(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
        session.invalidate();
        
        return "index.xhtml";
    }
        
    
    
    private Mensagem mensagem = new Mensagem();
    
  public String efetuarLogin() throws ClassNotFoundException, SQLException {
      
      String usuarioAp = mensagem.getUsuario();
      String senhaAp = mensagem.getSenha();
      
      String usuarioDB = "";
      String senhaDB = "";
      
      Connection conexao;
      
      String sql = "SELECT * FROM mensagem WHERE usuario = ? AND senha = ?";
      
      try{
      conexao = FabricaConexao.getConexao();
      
      PreparedStatement stmt = conexao.prepareStatement(sql);
      
            stmt.setString(1, usuarioAp);
            stmt.setString(2, senhaAp);
          
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {
                usuarioDB = rs.getString("usuario");
                senhaDB = rs.getString("senha");
            }
            
      rs.close();
      stmt.close();
      
      }catch(SQLException e){
          e.printStackTrace();
      }
      
      if(usuarioDB.equals(mensagem.getUsuario()) && senhaDB.equals(mensagem.getSenha())) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("usuario", usuarioDB);
      return "home.xhtml";
      
    }else{
     //FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
     // FacesContext.getCurrentInstance().addMessage("errro", msg);
      return "index.xhtml";
    }
  }

  public Mensagem getMensagem() {
    return mensagem;
  }

  public void setMensagem(Mensagem mensagem) {
    this.mensagem = mensagem;
  }
}
