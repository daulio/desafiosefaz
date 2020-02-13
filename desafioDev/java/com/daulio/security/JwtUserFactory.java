package com.daulio.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.daulio.entity.Usuario;
import com.daulio.enums.TipoPerfil;


public class JwtUserFactory {

    /**
     * Converte e gera um JwtUser com base nos dados de um funcionário.
     *
     * @param usuario
     * @return JwtUser
     */
    public static JwtUser create(Usuario usuario) {
        return new JwtUser(usuario.getId(), usuario.getNome(), usuario.getSenha(), mapToGrantedAuthorities(TipoPerfil.ROLE_ADMIN));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(TipoPerfil perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }

}
