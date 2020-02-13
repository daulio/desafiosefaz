package com.daulio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daulio.entity.Telefone;
import com.daulio.entity.Usuario;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	List<Telefone> findByUsuario(Usuario usuario);
}
