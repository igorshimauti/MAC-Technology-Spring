package br.com.mactechnology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mactechnology.model.Usuario;
import br.com.mactechnology.repository.UsuarioRepository;
import br.com.mactechnology.service.exception.BusinessRulesException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioJaCadastrado = usuarioRepository.findByEmail(usuario.getEmail()).orElse(usuario);

		if (usuarioJaCadastrado.getId() != usuario.getId()) {
			throw new BusinessRulesException("Usuário com o login '" + usuario.getEmail() + "' já foi cadastrado anteriormente.");
		}
		
		if (usuario.getId() == null) {
			usuario.setAutorizado(false);
		}

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}

	@Transactional(readOnly = true)
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new BusinessRulesException("Usuário não encontrado."));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username).orElseThrow(() -> new BusinessRulesException("Usuário não encontrado."));
	}

}
