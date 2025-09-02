package io.github.vagnerbraga.sbootexpsecurity.domain.repository;

import io.github.vagnerbraga.sbootexpsecurity.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
