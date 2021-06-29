package br.com.mactechnology.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> findByAutorizado(Boolean autorizado);
}
