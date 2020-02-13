package com.daulio.desafiofront.beans;


import com.daulio.desafiofront.dto.JwtAuthenticationDTO;
import com.daulio.desafiofront.dto.TokenDTO;
import com.daulio.desafiofront.servico.UsuarioService;
import com.daulio.desafiofront.util.FacesUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AutenticacaoBean implements Serializable{
    
    private TokenDTO tokenDTO;
    private JwtAuthenticationDTO loginDTO;
    
    @PostConstruct
    public void init(){
        loginDTO = new JwtAuthenticationDTO();
    }

    public TokenDTO getTokenDTO() {
        return tokenDTO;
    }

    public void setTokenDTO(TokenDTO tokenDTO) {
        this.tokenDTO = tokenDTO;
    }

    public JwtAuthenticationDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(JwtAuthenticationDTO loginDTO) {
        this.loginDTO = loginDTO;
    }
    
    
    public String autenticar(){
        String retorno ="";
        UsuarioService service = new UsuarioService();
        try {
            tokenDTO = service.autenticar(loginDTO);
            if (tokenDTO == null) {
                FacesUtil.adicionarMsgErro("Acesso negado!");
            } else {
                retorno = "/principal.xhtml?faces-redirect=true";
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgErro("Erro ao tentar autenticar no sistema!");
        }
        return retorno;
    }
}
