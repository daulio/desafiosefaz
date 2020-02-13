package com.daulio.desafiofront.beans;


import com.daulio.desafiofront.dto.TelefoneDTO;
import com.daulio.desafiofront.dto.TokenDTO;
import com.daulio.desafiofront.dto.UsuarioDTO;
import com.daulio.desafiofront.enums.TipoTelefone;
import com.daulio.desafiofront.servico.UsuarioService;
import com.daulio.desafiofront.util.FacesUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@SessionScoped
public class UsuarioBeans implements Serializable{
    
    private UsuarioDTO usuarioCadastrado;
    private TelefoneDTO telefone;
    private List<UsuarioDTO> listaUsuarios;
    private List<TelefoneDTO> listaTelefones;
    
    private String tipo;
    private String acao;
    private String foneacao;
    private Integer id;
    private Long idTelefone;
    
    @PostConstruct
    public void init(){
        usuarioCadastrado = new UsuarioDTO();
        telefone = new TelefoneDTO();
        listaTelefones = new ArrayList<TelefoneDTO>();
        usuarioCadastrado.setTelefones(listaTelefones);
        listaUsuarios = new ArrayList<UsuarioDTO>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    public TelefoneDTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDTO telefone) {
        this.telefone = telefone;
    }

    public String getFoneacao() {
        return foneacao;
    }

    public void setFoneacao(String foneacao) {
        this.foneacao = foneacao;
    }
    
    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioCadastrado() {
        return usuarioCadastrado;
    }

    public void setUsuarioCadastrado(UsuarioDTO usuarioCadastrado) {
        this.usuarioCadastrado = usuarioCadastrado;
    }

    public List<TelefoneDTO> getListaTelefones() {
        return listaTelefones;
    }

    public void setListaTelefones(List<TelefoneDTO> listaTelefones) {
        this.listaTelefones = listaTelefones;
    }
    
    public void novo(){
        usuarioCadastrado = new UsuarioDTO();
        telefone = new TelefoneDTO();
        acao = "Novo";
    }
    
    public void novoTelefone(){
	telefone = new TelefoneDTO();
        foneacao = "Novo";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("telefoneCadastro.xhtml");
        } catch (IOException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar voltar página!");
        }
    }
    
    public void salvar(TokenDTO token){
        try {
            UsuarioService service = new UsuarioService();
            this.usuarioCadastrado.setTelefones(listaTelefones);
	    this.usuarioCadastrado = service.salvar(token, usuarioCadastrado);
            voltar(token, "principal.xhtml");
            FacesUtil.adicionarMsgInfo("Usuário salvo com sucesso!");
	} catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar salvar Usuários");
	}      
    }
    
    public void salvarTelefone(TokenDTO token){
        telefone.setTipoTelefone(TipoTelefone.getByName(this.tipo));
        listaTelefones.add(telefone);
        voltar(token, "usuarioCadastro.xhtml");
    }
    
    public void excluir(TokenDTO token){
        try {
            if (id != null){
                UsuarioService service = new UsuarioService();
                service.excluir(token, id);
                FacesUtil.adicionarMsgInfo("Usuário excluído com sucesso!");
                voltar(token, "principal.xhtml");
            }else{
		FacesUtil.adicionarMsgErro("Informe um Usuário válido!");
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar excluir Usuário!");
	}        
    }
    
    public void excluirTelefone(TokenDTO token){
        this.listaTelefones.remove(telefone);
        editarVoltando(token, "usuarioCadastro.xhtml");
    }
    
    public void editar(TokenDTO token){
        try {
            UsuarioService service = new UsuarioService();
            this.usuarioCadastrado.setTelefones(listaTelefones);
	    this.usuarioCadastrado = service.alterar(token, usuarioCadastrado);
            FacesUtil.adicionarMsgInfo("Usuário alterado com sucesso!");
            voltar(token, "principal.xhtml");
	} catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar alterar Usuários");
	}      
    }
    
    public void editarTelefone(TokenDTO token){
        telefone.setTipoTelefone(TipoTelefone.getByName(this.tipo));
        for (TelefoneDTO fone : listaTelefones) {
            if(fone.getId() == telefone.getId()){
                listaTelefones.remove(fone);
                listaTelefones.add(telefone);
            }
        }

        editarVoltando(token, "usuarioCadastro.xhtml");
    }
    
    private void editarVoltando(TokenDTO token, String pagina){
        try {
            UsuarioService service = new UsuarioService();
            this.usuarioCadastrado.setTelefones(listaTelefones);
	    this.usuarioCadastrado = service.alterar(token, usuarioCadastrado);
            FacesUtil.adicionarMsgInfo("Usuário alterado com sucesso!");
	} catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar alterar Usuários");
	}       
        voltar(token, pagina); 
    }
    
    public void carregar(TokenDTO token){
        try {
            UsuarioService service = new UsuarioService();
	    this.listaUsuarios = service.listarTodos(token);
	} catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar listar Usuários");
	}        
    }
    
   public void carregarTelefone(TokenDTO token){
       if(!foneacao.equals("Novo")){
            UsuarioService service = new UsuarioService();
            this.usuarioCadastrado = service.getUsuarioById(token, id);
            listaTelefones = this.usuarioCadastrado.getTelefones();
            for (TelefoneDTO fone : this.usuarioCadastrado.getTelefones()) {
                 if(fone.getId() == idTelefone){
                     telefone = fone;
                 }
             }
       }else{
           telefone = new TelefoneDTO();
       }
   }
    
    public void carregarCadastro(TokenDTO token){
        if(acao.equals("Novo")){
            if(usuarioCadastrado == null){
                this.usuarioCadastrado = new UsuarioDTO();
            }
            this.usuarioCadastrado.setTelefones(this.listaTelefones);
        }else{
            try {
                if (id != null){
                    UsuarioService service = new UsuarioService();
                    this.usuarioCadastrado = service.getUsuarioById(token, id);
                    this.listaTelefones = this.usuarioCadastrado.getTelefones();
                    if(this.listaTelefones.size() == 0){
                        telefone = new TelefoneDTO();
                    }
                }else{
                    usuarioCadastrado = new UsuarioDTO();
                }
            } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar obter dados do Usuário!");
            } 
        }
    }

    public void voltar(TokenDTO token, String pagina){
        try {
            carregarCadastro(token);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar voltar página!");
        }
    }
    

}
