package com.daulio.desafiofront.servico;

import com.daulio.desafiofront.dto.JwtAuthenticationDTO;
import com.daulio.desafiofront.dto.TokenDTO;
import com.daulio.desafiofront.dto.UsuarioDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioService {
    
    private static final String URL_API = "http://localhost:8281";
    private static final int HTTP_COD_SUCESSO = 200;
    private static final int HTTP_CREATE = 201;
    private final Gson gson = new Gson();
    private String json;
    
    public TokenDTO autenticar(JwtAuthenticationDTO login){
        
        try{
            TokenDTO tokenDTO = new TokenDTO();
            json = gson.toJson(login);
            BufferedReader br;
            
            URL url = new URL(URL_API+"/auth");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(json);
            out.close();
            InputStream in = con.getInputStream();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(in));
            try {
                tokenDTO = gson.fromJson(br, TokenDTO.class);
            } finally {
                con.disconnect();
                br.close();
                in.close();
            }
             
            return tokenDTO;
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public List<UsuarioDTO> listarTodos(TokenDTO token){
        List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        try{
            BufferedReader br;
           
            URL url = new URL(URL_API+"/usuario/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer "+ token.getToken());

            InputStream in = con.getInputStream();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(in));
            try {
                usuarios = gson.fromJson(br, new TypeToken<List<UsuarioDTO>>(){}.getType());
            } finally {
                con.disconnect();
                br.close();
                in.close();
            }
             
            return usuarios;
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public UsuarioDTO getUsuarioById(TokenDTO token, int id){
        UsuarioDTO usuario = new UsuarioDTO();
        try{
            BufferedReader br;
           
            URL url = new URL(URL_API+"/usuario/"+String.valueOf(id));
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer "+ token.getToken());

            InputStream in = con.getInputStream();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(in));
            try {
                usuario = gson.fromJson(br, UsuarioDTO.class);
            } finally {
                con.disconnect();
                br.close();
                in.close();
            }
             
            return usuario;
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public UsuarioDTO salvar(TokenDTO token, UsuarioDTO dto){
        try{
            json = gson.toJson(dto);
            
            BufferedReader br;
            
            URL url = new URL(URL_API+"/usuario/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer "+ token.getToken());

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(json);
            out.close();
            InputStream in = con.getInputStream();
            if (con.getResponseCode() != HTTP_CREATE) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(in));
            try {
                dto = gson.fromJson(br, UsuarioDTO.class);
            } finally {
                con.disconnect();
                br.close();
                in.close();
            }
             
            return dto;
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public UsuarioDTO alterar(TokenDTO token, UsuarioDTO dto){
        try{
            json = gson.toJson(dto);
            BufferedReader br;
            
            URL url = new URL(URL_API+"/usuario/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer "+ token.getToken());

            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(json);
            out.close();
            InputStream in = con.getInputStream();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            br = new BufferedReader(new InputStreamReader(in));
            try {
                dto = gson.fromJson(br, UsuarioDTO.class);
            } finally {
                con.disconnect();
                br.close();
                in.close();
            }
             
            return dto;
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public void excluir(TokenDTO token, int id){
        try{
            BufferedReader br;
           
            URL url = new URL(URL_API+"/usuario/"+String.valueOf(id));
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer "+ token.getToken());
            
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
            }
            
        }catch (MalformedURLException e) {
        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
