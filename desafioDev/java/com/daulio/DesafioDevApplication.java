package com.daulio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.daulio.entity.Telefone;
import com.daulio.entity.Usuario;
import com.daulio.enums.TipoTelefone;
import com.daulio.repository.TelefoneRepository;
import com.daulio.repository.UsuarioRepository;


@SpringBootApplication
public class DesafioDevApplication extends SpringBootServletInitializer {
	
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private TelefoneRepository telefoneRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DesafioDevApplication.class);
    }
	
	@Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        
            Optional<Usuario> findByNome = usuarioRepository.findByNome("admin");
            System.out.println("buscou usuario admin -> " + findByNome);
            if (!findByNome.isPresent()) {
            	
                Usuario usuario = new Usuario();
                usuario.setNome("admin");
                usuario.setSenha("123456");
                usuario.setEmail("admin@test.com.br");
                
                
                Telefone telefone = new Telefone();
                telefone.setDdd("81");
                telefone.setNumero("991912020");
                telefone.setTipoTelefone(TipoTelefone.CELULAR.value);
                telefone.setUsuario(usuario);
                List<Telefone> telefones = new ArrayList<>();
                telefones.add(telefone);
                usuario.setTelefones(telefones);
                this.usuarioRepository.save(usuario);

                telefone.setUsuario(usuario);
                this.telefoneRepository.save(telefone);
                

                System.out.println("salvou usuario");
                findByNome = usuarioRepository.findByNome("admin");
                telefones = telefoneRepository.findByUsuario(usuario);
                findByNome.get().setTelefones(telefones);
                
                System.out.println("buscou usuario admin -> " + findByNome);
            }
        };
    }

}
