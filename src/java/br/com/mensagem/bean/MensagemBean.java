package br.com.mensagem.bean;

import br.com.mensagem.DAO.MensagemDAO;
import br.com.mensagem.entidade.Mensagem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MensagemBean {

    private Mensagem mensagem = new Mensagem();//objeto que herda os metodos get e set da classe Mensagem
    private MensagemDAO msg_dao = new MensagemDAO();//objeto que herda os metodos inserir deletar e listar e excluir da 
    //da classe MensagemDAO

    private List<Mensagem> listamsg = new ArrayList<>();

    public void cadastrar() throws ClassNotFoundException, SQLException {
        // getMsg_dao().inserir(getMensagem());//executando o metodo inserir da classe DAO
        // setMensagem(new Mensagem());//passando o objeto mensagem para limpar a memoria
        new MensagemDAO().inserir(mensagem);
        mensagem = new Mensagem();
        listar();
    }

    public void listar() throws ClassNotFoundException, SQLException {
        listamsg = msg_dao.selecionarTudo();
    }
    
    
    
    public void alterar(Mensagem m){
        mensagem = m;
    }
 
    //deletar bean
    public void deletar(Mensagem mensagem) throws ClassNotFoundException,SQLException{
        new MensagemDAO().deletar(mensagem);
        listar();
    }
    
    
    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public MensagemDAO getMsg_dao() {
        return msg_dao;
    }

    public void setMsg_dao(MensagemDAO msg_dao) {
        this.msg_dao = msg_dao;
    }

    public List<Mensagem> getListamsg() {
        return listamsg;
    }

    public void setListamsg(List<Mensagem> listamsg) {
        this.listamsg = listamsg;
    }

}
