package com.daulio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daulio.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByNome(String nome);
}
